package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final DeviceCatalogItemRepository deviceCatalogItemRepository;

    // ✅ Constructor expected by TESTS
    @Autowired
    public IssuedDeviceRecordServiceImpl(
            IssuedDeviceRecordRepository issuedRepo,
            EmployeeProfileRepository employeeProfileRepository,
            DeviceCatalogItemRepository deviceCatalogItemRepository) {

        this.issuedRepo = issuedRepo;
        this.employeeProfileRepository = employeeProfileRepository;
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long id) {
        IssuedDeviceRecord record = issuedRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("Device already returned");
        }

        record.setStatus("RETURNED");
        return issuedRepo.save(record);
    }
}
