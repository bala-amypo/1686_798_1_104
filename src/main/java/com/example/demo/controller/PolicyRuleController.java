package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PolicyRule;
import com.example.demo.service.PolicyRuleService;

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
