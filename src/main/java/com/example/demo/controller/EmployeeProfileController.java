package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    
    @PostMapping
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        return service.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getEmployee(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    
    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}/status")
    public EmployeeProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updateEmployeeStatus(id, active);
    }
}
