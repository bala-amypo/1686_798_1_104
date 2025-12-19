 package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.EmployeeProfile;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    Optional<EmployeeProfile> findByEmail(String email);
}
