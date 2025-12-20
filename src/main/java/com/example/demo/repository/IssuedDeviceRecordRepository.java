package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.IssuedDeviceRecord;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    Long countActiveDevicesForEmployee(Long employeeld);

    Optional<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            Long employeeld,
            Long deviceltemld
    );

    List<IssuedDeviceRecord> findByEmployeeld(Long employeeld);
}
