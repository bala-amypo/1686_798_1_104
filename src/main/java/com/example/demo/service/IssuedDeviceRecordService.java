package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.IssuedDeviceRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IssuedDeviceRecordRepository;

@Service
public class IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repository;

    public IssuedDeviceRecordService(IssuedDeviceRecordRepository repository) {
        this.repository = repository;
    }

    // Issue device
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        record.setIssuedDate(LocalDate.now());
        record.setReturnedDate(null);
        record.setStatus("ISSUED");

        return repository.save(record);
    }

    // Return device
    public IssuedDeviceRecord returnDevice(Long recordld) {

        IssuedDeviceRecord record = repository.findById(recordld)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (record.getReturnedDate() != null) {
            throw new BadRequestException("already returned");
        }

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");

        return repository.save(record);
    }

    // Get issued devices by employee
    public List<IssuedDeviceRecord> getlssuedDevicesByEmployee(Long employeeld) {
        return repository.findByEmployeeld(employeeld);
    }
}
