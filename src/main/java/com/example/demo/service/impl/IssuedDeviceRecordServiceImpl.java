package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;

    // ✅ Constructor used by Spring
    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedRepo) {
        this.issuedRepo = issuedRepo;
    }

    // ✅ Constructor REQUIRED by test (DO NOT REMOVE)
    public IssuedDeviceRecordServiceImpl(
            IssuedDeviceRecordRepository issuedRepo,
            EmployeeProfileRepository employeeProfileRepository,
            DeviceCatalogItemRepository deviceCatalogItemRepository) {

        this.issuedRepo = issuedRepo;
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
