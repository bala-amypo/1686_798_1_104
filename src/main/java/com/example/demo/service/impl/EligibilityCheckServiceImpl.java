package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {
    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository eligibilityRepo;

    public EligibilityCheckServiceImpl(EmployeeProfileRepository employeeRepo,
                                     DeviceCatalogItemRepository deviceRepo,
                                     IssuedDeviceRecordRepository issuedRepo,
                                     PolicyRuleRepository policyRepo,
                                     EligibilityCheckRecordRepository eligibilityRepo) {
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.policyRepo = policyRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);

        Optional<EmployeeProfile> empOpt = employeeRepo.findById(employeeId);
        Optional<DeviceCatalogItem> devOpt = deviceRepo.findById(deviceItemId);

        if (empOpt.isEmpty() || devOpt.isEmpty()) {
            record.setIsEligible(false);
            record.setReason("Employee or device not found");
            return eligibilityRepo.save(record);
        }

        EmployeeProfile employee = empOpt.get();
        DeviceCatalogItem device = devOpt.get();

        if (!employee.getActive()) {
            record.setIsEligible(false);
            record.setReason("Employee is not active");
            return eligibilityRepo.save(record);
        }

        if (!device.getActive()) {
            record.setIsEligible(false);
            record.setReason("Device is inactive");
            return eligibilityRepo.save(record);
        }

        List<IssuedDeviceRecord> activeAssignments = issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId);
        if (!activeAssignments.isEmpty()) {
            record.setIsEligible(false);
            record.setReason("Active issuance already exists for this device");
            return eligibilityRepo.save(record);
        }

        Long currentDeviceCount = issuedRepo.countActiveDevicesForEmployee(employeeId);
        if (currentDeviceCount >= device.getMaxAllowedPerEmployee()) {
            record.setIsEligible(false);
            record.setReason("Maximum allowed devices reached for this device type");
            return eligibilityRepo.save(record);
        }

        List<PolicyRule> activeRules = policyRepo.findByActiveTrue();
        for (PolicyRule rule : activeRules) {
            boolean applies = false;
            
            if (rule.getAppliesToDepartment() == null && rule.getAppliesToRole() == null) {
                applies = true;
            } else if (rule.getAppliesToDepartment() != null && rule.getAppliesToDepartment().equals(employee.getDepartment())) {
                applies = true;
            } else if (rule.getAppliesToRole() != null && rule.getAppliesToRole().equals(employee.getJobRole())) {
                applies = true;
            }
            
            if (applies && currentDeviceCount >= rule.getMaxDevicesAllowed()) {
                record.setIsEligible(false);
                record.setReason("Policy violation: " + rule.getRuleCode());
                return eligibilityRepo.save(record);
            }
        }

        record.setIsEligible(true);
        record.setReason("Eligible");
        return eligibilityRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }
}