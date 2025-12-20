package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.IssuedDeviceRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repository;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        repository.findActiveByEmployeeAndDevice(
                record.getEmployeeld(),
                record.getDeviceltemld()
        ).ifPresent(r -> {
            throw new BadRequestException("already returned");
        });

        record.setIssuedDate(LocalDate.now());
        record.setReturnedDate(null);
        record.setStatus("ISSUED");

        return repository.save(record);
    }

    @Override
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

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeld) {
        return repository.findByEmployeeld(employeeld);
    }
}
