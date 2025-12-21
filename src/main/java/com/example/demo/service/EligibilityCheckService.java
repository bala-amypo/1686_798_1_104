package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EligibilityCheckRecord;

public interface EligibilityCheckService {

    EligibilityCheckRecord validateEligibility(Long employeeld, Long deviceltemld);

    List<EligibilityCheckRecord> getChecksByEmployee(Long employeeld);
}
