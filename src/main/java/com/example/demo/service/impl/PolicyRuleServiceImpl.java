package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PolicyRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository repository;

    public PolicyRuleServiceImpl(PolicyRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {

        // Duplicate Rule Code check
        repository.findByRuleCode(rule.getRuleCode())
            .ifPresent(r -> {
                throw new BadRequestException("Rule code already exists");
            });

        // Invalid max limit
        if (rule.getMaxDevicesAllowed() != null && rule.getMaxDevicesAllowed() < 0) {
            throw new BadRequestException("maxAllowedPerEmployee");
        }

        return repository.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}
