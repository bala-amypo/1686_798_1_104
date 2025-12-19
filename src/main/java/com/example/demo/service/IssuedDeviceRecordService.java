package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile employee);

    EmployeeProfile getEmployeeByld(Long id);

    List<EmployeeProfile> getAllEmployees();

    EmployeeProfile updateEmployeeStatus(Long id, boolean active);
}
