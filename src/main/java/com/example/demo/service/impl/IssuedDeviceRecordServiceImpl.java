package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.IssuedDeviceRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repository;

    // ✅ Constructor Injection (CORRECT)
    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        IssuedDeviceRecord existing =
                repository.findByEmployeeIdAndDeviceItemIdAndReturnedDateIsNull(
                        record.getEmployeeId(),
                        record.getDeviceItemId()
                );

        if (existing != null) {
            throw new BadRequestException("already returned");
        }

        record.setIssuedDate(LocalDate.now());
        record.setStatus("ISSUED");

        return repository.save(record);
    }

    @Override
    @Transactional
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = repository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (record.getReturnedDate() != null) {
            throw new BadRequestException("already returned");
        }

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");

        return repository.save(record);
    }


    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
