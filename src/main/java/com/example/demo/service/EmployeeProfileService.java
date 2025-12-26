package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfile createEmployee(EmployeeProfile employee);
    EmployeeProfile updateEmployeeStatus(Long id, Boolean active);
    EmployeeProfile getEmployeeById(Long id);
    List<EmployeeProfile> getAllEmployees();
}