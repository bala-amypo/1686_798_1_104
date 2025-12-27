// package com.example.demo.controller;

// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.service.EmployeeProfileService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/employees")
// public class EmployeeProfileController {

//     private final EmployeeProfileService service;

//     public EmployeeProfileController(EmployeeProfileService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public EmployeeProfile create(@RequestBody EmployeeProfile employee) {
//         return service.createEmployee(employee);
//     }

//     @GetMapping("/{id}")
//     public EmployeeProfile getById(@PathVariable Long id) {
//         return service.getEmployeeById(id);
//     }

//     @GetMapping
//     public List<EmployeeProfile> getAll() {
//         return service.getAllEmployees();
//     }

//     @PutMapping("/{id}/status")
//     public EmployeeProfile updateStatus(
//             @PathVariable Long id,
//             @RequestParam boolean active) {
//         return service.updateEmployeeStatus(id, active);
//     }
// }

package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeProfileService employeeService;

    // @GetMapping("/")
    @GetMapping
    public ResponseEntity<List<EmployeeProfile>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // @PostMapping("/")
    @PostMapping
    public ResponseEntity<EmployeeProfile> createEmployee(@RequestBody EmployeeProfile employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
