package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }
    @PostMapping("/validate/{employeeld}/{deviceltemld}")
    public EligibilityCheckRecord validate(
            @PathVariable Long employeeld,
            @PathVariable Long deviceltemld) {
        return service.validateEligibility(employeeld, deviceltemld);
    }

    @GetMapping("/employee/{employeeld}")
    public List<EligibilityCheckRecord> getByEmployee(@PathVariable Long employeeld) {
        return service.getChecksByEmployee(employeeld);
    }
}
