package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyRuleController {

    @Autowired
    private PolicyRuleService ruleService;

    @GetMapping("/")
    public ResponseEntity<List<PolicyRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @PostMapping("/")
    public ResponseEntity<PolicyRule> createRule(@RequestBody PolicyRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
}