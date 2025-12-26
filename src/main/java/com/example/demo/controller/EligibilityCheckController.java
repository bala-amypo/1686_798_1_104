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