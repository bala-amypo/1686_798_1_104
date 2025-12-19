package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.IssuedDeviceRecord;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    long countActiveDevicesForEmployee(Long employeeld);

    List<IssuedDeviceRecord> findByEmployeeld(Long employeeld);
}
