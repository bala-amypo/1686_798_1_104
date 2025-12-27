// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.repository.EmployeeProfileRepository;
// import com.example.demo.service.EmployeeProfileService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class EmployeeProfileServiceImpl implements EmployeeProfileService {

//     private final EmployeeProfileRepository repo;

//     public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public EmployeeProfile createEmployee(EmployeeProfile emp) {
//         if (repo.findByEmployeeId(emp.getEmployeeId()).isPresent()) {
//             throw new BadRequestException("EmployeeId already exists");
//         }
//         if (repo.findByEmail(emp.getEmail()).isPresent()) {
//             throw new BadRequestException("Email already exists");
//         }
//         return repo.save(emp);
//     }

//     @Override
//     public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
//         EmployeeProfile emp = repo.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//         emp.setActive(active);
//         return repo.save(emp);
//     }

//     @Override
//     public EmployeeProfile getEmployeeById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//     }

//     @Override
//     public List<EmployeeProfile> getAllEmployees() {
//         return repo.findAll();
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.repository.EmployeeProfileRepository;
// import com.example.demo.service.EmployeeProfileService;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class EmployeeProfileServiceImpl implements EmployeeProfileService {
//     private final EmployeeProfileRepository employeeRepo;

//     public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
//         this.employeeRepo = employeeRepo;
//     }

//     @Override
//     public EmployeeProfile createEmployee(EmployeeProfile employee) {
//         if (employeeRepo.findByEmployeeId(employee.getEmployeeId()).isPresent()) {
//             throw new BadRequestException("EmployeeId already exists");
//         }
//         if (employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
//             throw new BadRequestException("Email already exists");
//         }
//         return employeeRepo.save(employee);
//     }

//     @Override
//     public EmployeeProfile updateEmployeeStatus(Long id, Boolean active) {
//         EmployeeProfile employee = employeeRepo.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//         employee.setActive(active);
//         return employeeRepo.save(employee);
//     }

//     @Override
//     public EmployeeProfile getEmployeeById(Long id) {
//         return employeeRepo.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//     }

//     @Override
//     public List<EmployeeProfile> getAllEmployees() {
//         return employeeRepo.findAll();
//     }
// }

package com.example.demo.service;

import java.util.List;
import com.example.demo.model.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile employee);

    EmployeeProfile getEmployeeById(Long id);

    List<EmployeeProfile> getAllEmployees();

    EmployeeProfile updateEmployeeStatus(Long id, boolean active);

    void deleteEmployee(Long id);
}
