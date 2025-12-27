// package com.example.demo.service.impl;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.EligibilityCheckService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class EligibilityCheckServiceImpl implements EligibilityCheckService {

//     private final EmployeeProfileRepository employeeRepo;
//     private final DeviceCatalogItemRepository deviceRepo;
//     private final IssuedDeviceRecordRepository issuedRepo;
//     private final PolicyRuleRepository policyRepo;
//     private final EligibilityCheckRecordRepository eligibilityRepo;

//     public EligibilityCheckServiceImpl(
//             EmployeeProfileRepository employeeRepo,
//             DeviceCatalogItemRepository deviceRepo,
//             IssuedDeviceRecordRepository issuedRepo,
//             PolicyRuleRepository policyRepo,
//             EligibilityCheckRecordRepository eligibilityRepo
//     ) {
//         this.employeeRepo = employeeRepo;
//         this.deviceRepo = deviceRepo;
//         this.issuedRepo = issuedRepo;
//         this.policyRepo = policyRepo;
//         this.eligibilityRepo = eligibilityRepo;
//     }

//     @Override
//     public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

//         EligibilityCheckRecord rec = new EligibilityCheckRecord();
//         rec.setEmployeeId(employeeId);
//         rec.setDeviceItemId(deviceItemId);

//         Optional<EmployeeProfile> empOpt = employeeRepo.findById(employeeId);
//         Optional<DeviceCatalogItem> devOpt = deviceRepo.findById(deviceItemId);

//         if (empOpt.isEmpty() || devOpt.isEmpty()) {
//             rec.setIsEligible(false);
//             rec.setReason("Employee or Device not found");
//             return eligibilityRepo.save(rec);
//         }

//         EmployeeProfile emp = empOpt.get();
//         DeviceCatalogItem dev = devOpt.get();

//         if (!emp.getActive()) {
//             rec.setIsEligible(false);
//             rec.setReason("Employee not active");
//             return eligibilityRepo.save(rec);
//         }

//         if (!dev.getActive()) {
//             rec.setIsEligible(false);
//             rec.setReason("Device inactive");
//             return eligibilityRepo.save(rec);
//         }

//         if (!issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
//             rec.setIsEligible(false);
//             rec.setReason("Active issuance already exists");
//             return eligibilityRepo.save(rec);
//         }

//         Long issuedCount = issuedRepo.countActiveDevicesForEmployee(employeeId);

//         if (dev.getMaxAllowedPerEmployee() != null &&
//                 issuedCount >= dev.getMaxAllowedPerEmployee()) {
//             rec.setIsEligible(false);
//             rec.setReason("Maximum allowed devices reached");
//             return eligibilityRepo.save(rec);
//         }

//         List<PolicyRule> rules = policyRepo.findByActiveTrue();

//         for (PolicyRule rule : rules) {

//             boolean deptMatch =
//                     rule.getAppliesToDepartment() == null ||
//                             rule.getAppliesToDepartment().equals(emp.getDepartment());

//             boolean roleMatch =
//                     rule.getAppliesToRole() == null ||
//                             rule.getAppliesToRole().equals(emp.getJobRole());

//             if (deptMatch && roleMatch) {
//                 if (rule.getMaxDevicesAllowed() != null &&
//                         issuedCount >= rule.getMaxDevicesAllowed()) {
//                     rec.setIsEligible(false);
//                     rec.setReason("Policy violation");
//                     return eligibilityRepo.save(rec);
//                 }
//             }
//         }

//         rec.setIsEligible(true);
//         rec.setReason("Eligible");
//         return eligibilityRepo.save(rec);
//     }

//     @Override
//     public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
//         return eligibilityRepo.findByEmployeeId(employeeId);
//     }
// }
// package com.example.demo.service.impl;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.EligibilityCheckService;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class EligibilityCheckServiceImpl implements EligibilityCheckService {
//     private final EmployeeProfileRepository employeeRepo;
//     private final DeviceCatalogItemRepository deviceRepo;
//     private final IssuedDeviceRecordRepository issuedRepo;
//     private final PolicyRuleRepository policyRepo;
//     private final EligibilityCheckRecordRepository eligibilityRepo;

//     public EligibilityCheckServiceImpl(EmployeeProfileRepository employeeRepo,
//                                      DeviceCatalogItemRepository deviceRepo,
//                                      IssuedDeviceRecordRepository issuedRepo,
//                                      PolicyRuleRepository policyRepo,
//                                      EligibilityCheckRecordRepository eligibilityRepo) {
//         this.employeeRepo = employeeRepo;
//         this.deviceRepo = deviceRepo;
//         this.issuedRepo = issuedRepo;
//         this.policyRepo = policyRepo;
//         this.eligibilityRepo = eligibilityRepo;
//     }

//     @Override
//     public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
//         EligibilityCheckRecord record = new EligibilityCheckRecord();
//         record.setEmployeeId(employeeId);
//         record.setDeviceItemId(deviceItemId);

//         Optional<EmployeeProfile> empOpt = employeeRepo.findById(employeeId);
//         Optional<DeviceCatalogItem> devOpt = deviceRepo.findById(deviceItemId);

//         if (empOpt.isEmpty() || devOpt.isEmpty()) {
//             record.setIsEligible(false);
//             record.setReason("Employee or device not found");
//             return eligibilityRepo.save(record);
//         }

//         EmployeeProfile employee = empOpt.get();
//         DeviceCatalogItem device = devOpt.get();

//         if (!employee.getActive()) {
//             record.setIsEligible(false);
//             record.setReason("Employee is not active");
//             return eligibilityRepo.save(record);
//         }

//         if (!device.getActive()) {
//             record.setIsEligible(false);
//             record.setReason("Device is inactive");
//             return eligibilityRepo.save(record);
//         }

//         List<IssuedDeviceRecord> activeAssignments = issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId);
//         if (!activeAssignments.isEmpty()) {
//             record.setIsEligible(false);
//             record.setReason("Active issuance already exists for this device");
//             return eligibilityRepo.save(record);
//         }

//         Long currentDeviceCount = issuedRepo.countActiveDevicesForEmployee(employeeId);
//         if (currentDeviceCount >= device.getMaxAllowedPerEmployee()) {
//             record.setIsEligible(false);
//             record.setReason("Maximum allowed devices reached for this device type");
//             return eligibilityRepo.save(record);
//         }

//         List<PolicyRule> activeRules = policyRepo.findByActiveTrue();
//         for (PolicyRule rule : activeRules) {
//             boolean applies = false;
            
//             if (rule.getAppliesToDepartment() == null && rule.getAppliesToRole() == null) {
//                 applies = true;
//             } else if (rule.getAppliesToDepartment() != null && rule.getAppliesToDepartment().equals(employee.getDepartment())) {
//                 applies = true;
//             } else if (rule.getAppliesToRole() != null && rule.getAppliesToRole().equals(employee.getJobRole())) {
//                 applies = true;
//             }
            
//             if (applies && currentDeviceCount >= rule.getMaxDevicesAllowed()) {
//                 record.setIsEligible(false);
//                 record.setReason("Policy violation: " + rule.getRuleCode());
//                 return eligibilityRepo.save(record);
//             }
//         }

//         record.setIsEligible(true);
//         record.setReason("Eligible");
//         return eligibilityRepo.save(record);
//     }

//     @Override
//     public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
//         return eligibilityRepo.findByEmployeeId(employeeId);
//     }
// }

package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.model.PolicyRule;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.EligibilityCheckRecordRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.EligibilityCheckService;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    
    private final EligibilityCheckRecordRepository eligibilityRepo;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
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

    EligibilityCheckRecord rec = new EligibilityCheckRecord();
    rec.setEmployeeId(employeeId);
    rec.setDeviceItemId(deviceItemId);

    EmployeeProfile emp = employeeRepo.findById(employeeId).orElse(null);
    DeviceCatalogItem dev = deviceRepo.findById(deviceItemId).orElse(null);

    if (emp == null || dev == null) {
        rec.setIsEligible(false);
        rec.setReason("Employee or Device not found");
        return eligibilityRepo.save(rec);
    }

    if (!emp.getActive()) {
        rec.setIsEligible(false);
        rec.setReason("Employee not active");
        return eligibilityRepo.save(rec);
    }

    if (!dev.getActive()) {
        rec.setIsEligible(false);
        rec.setReason("Device inactive");
        return eligibilityRepo.save(rec);
    }

    if (!issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
        rec.setIsEligible(false);
        rec.setReason("Active issuance exists");
        return eligibilityRepo.save(rec);
    }

    long activeCount = issuedRepo.countActiveDevicesForEmployee(employeeId);
    if (activeCount >= dev.getMaxAllowedPerEmployee()) {
        rec.setIsEligible(false);
        rec.setReason("Maximum allowed devices reached");
        return eligibilityRepo.save(rec);
    }

    for (PolicyRule rule : policyRepo.findByActiveTrue()) {

        boolean deptOk = rule.getAppliesToDepartment() == null
                || rule.getAppliesToDepartment().equals(emp.getDepartment());

        boolean roleOk = rule.getAppliesToRole() == null
                || rule.getAppliesToRole().equals(emp.getJobRole());

        if (deptOk && roleOk) {
            if (activeCount >= rule.getMaxDevicesAllowed()) {
                rec.setIsEligible(false);
                rec.setReason("Policy violation");
                return eligibilityRepo.save(rec);
            }
        }
    }

    rec.setIsEligible(true);
    rec.setReason("Eligible");
    return eligibilityRepo.save(rec);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }

    @Override
    public EligibilityCheckRecord getById(Long id) {
        return eligibilityRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Eligibility check not found"));
    }
}

