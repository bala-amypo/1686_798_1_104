// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.model.PolicyRule;
// import com.example.demo.repository.PolicyRuleRepository;
// import com.example.demo.service.PolicyRuleService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class PolicyRuleServiceImpl implements PolicyRuleService {

//     private final PolicyRuleRepository repo;

//     public PolicyRuleServiceImpl(PolicyRuleRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public PolicyRule createRule(PolicyRule rule) {
//         if (repo.findByRuleCode(rule.getRuleCode()).isPresent()) {
//             throw new BadRequestException("Rule code already exists");
//         }
//         return repo.save(rule);
//     }

//     @Override
//     public List<PolicyRule> getAllRules() {
//         return repo.findAll();
//     }

//     @Override
//     public List<PolicyRule> getActiveRules() {
//         return repo.findByActiveTrue();
//     }
// }


// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.model.PolicyRule;
// import com.example.demo.repository.PolicyRuleRepository;
// import com.example.demo.service.PolicyRuleService;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class PolicyRuleServiceImpl implements PolicyRuleService {
//     private final PolicyRuleRepository policyRepo;

//     public PolicyRuleServiceImpl(PolicyRuleRepository policyRepo) {
//         this.policyRepo = policyRepo;
//     }

//     @Override
//     public PolicyRule createRule(PolicyRule rule) {
//         if (policyRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
//             throw new BadRequestException("Rule code already exists");
//         }
//         return policyRepo.save(rule);
//     }

//     @Override
//     public List<PolicyRule> getAllRules() {
//         return policyRepo.findAll();
//     }

//     @Override
//     public List<PolicyRule> getActiveRules() {
//         return policyRepo.findByActiveTrue();
//     }
// }

package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PolicyRule;

public interface PolicyRuleService {

    PolicyRule createRule(PolicyRule rule);

    List<PolicyRule> getAllRules();

    List<PolicyRule> getActiveRules();

    PolicyRule updateRuleActive(Long id, boolean active);

    void deleteRule(Long id);
}

