package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EligibilityCheckRecord;

public interface EligibilityCheckService {

    EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceltemId);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId);
}
