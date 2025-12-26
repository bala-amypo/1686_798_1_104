package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceController {

    @Autowired
    private IssuedDeviceRecordService issuedService;

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return ResponseEntity.ok(issuedService.returnDevice(id));
    }
}
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

    @GetMapping("/")
    public ResponseEntity<List<EmployeeProfile>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeProfile> createEmployee(@RequestBody EmployeeProfile employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityCheckService eligibilityService;

    @PostMapping("/validate")
    public ResponseEntity<EligibilityCheckRecord> validateEligibility(@RequestParam Long employeeId, @RequestParam Long deviceItemId) {
        return ResponseEntity.ok(eligibilityService.validateEligibility(employeeId, deviceItemId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EligibilityCheckRecord>> getChecksByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(eligibilityService.getChecksByEmployee(employeeId));
    }
}
package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceCatalogService deviceService;

    @GetMapping("/")
    public ResponseEntity<List<DeviceCatalogItem>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllItems());
    }

    @PostMapping("/")
    public ResponseEntity<DeviceCatalogItem> createDevice(@RequestBody DeviceCatalogItem device) {
        return ResponseEntity.ok(deviceService.createItem(device));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceCatalogItem> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
        return ResponseEntity.ok(deviceService.updateActiveStatus(id, active));
    }
}
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = new UserAccount();
        user.setId(1L);
        user.setEmail(request.getEmail());
        user.setRole("USER");
        
        String token = tokenProvider.generateToken(user);
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        
        return ResponseEntity.ok(response);
    }
}