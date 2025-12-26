Controller  AuthController.java
package com.example.demo.controller;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtProvider;

    public AuthController(
            UserAccountRepository userRepo,
            PasswordEncoder encoder,
            JwtTokenProvider jwtProvider) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        UserAccount user = new UserAccount();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPasswordHash(encoder.encode(request.getPassword()));
        userRepo.save(user);
        return "Registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        UserAccount user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtProvider.generateToken(user);
        return new AuthResponse(token);
    }
}
DeviceCatalogController.java
package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService service;

    public DeviceCatalogController(DeviceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    public DeviceCatalogItem create(@RequestBody DeviceCatalogItem item) {
        return service.createItem(item);
    }

    @PutMapping("/{id}/active")
    public DeviceCatalogItem updateActive(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updateActiveStatus(id, active);
    }

    @GetMapping
    public List<DeviceCatalogItem> getAll() {
        return service.getAllItems();
    }
}



EligibilityCheckController.java  package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    // POST /api/eligibility/validate/{employeeId}/{deviceItemId}
    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validateEligibility(
            @PathVariable Long employeeId,
            @PathVariable Long deviceItemId
    ) {
        return service.validateEligibility(employeeId, deviceItemId);
    }

    // GET /api/eligibility/employee/{employeeId}
    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getChecksByEmployee(
            @PathVariable Long employeeId
    ) {
        return service.getChecksByEmployee(employeeId);
    }
}

EmployeeProfileController.java  package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeProfile create(@RequestBody EmployeeProfile employee) {
        return service.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeProfile> getAll() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}/status")
    public EmployeeProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updateEmployeeStatus(id, active);
    }
}

IssuedDeviceRecordController.java

package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public IssuedDeviceRecord issue(@RequestBody IssuedDeviceRecord record) {
        return service.issueDevice(record);
    }

    @PutMapping("/{id}/return")
    public IssuedDeviceRecord returnDevice(@PathVariable Long id) {
        return service.returnDevice(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getByEmployee(@PathVariable Long employeeId) {
        return service.getIssuedDevicesByEmployee(employeeId);
    }
}

PolicyRuleController.java
 package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
public class PolicyRuleController {

    private final PolicyRuleService service;

    public PolicyRuleController(PolicyRuleService service) {
        this.service = service;
    }

    @PostMapping
    public PolicyRule create(@RequestBody PolicyRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<PolicyRule> getAll() {
        return service.getAllRules();
    }

    @GetMapping("/active")
    public List<PolicyRule> getActive() {
        return service.getActiveRules();
    }
}