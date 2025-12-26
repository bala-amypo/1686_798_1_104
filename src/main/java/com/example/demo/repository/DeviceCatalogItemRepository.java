package com.example.demo.repository;

import com.example.demo.model.DeviceCatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceCatalogItemRepository extends JpaRepository<DeviceCatalogItem, Long> {

    Optional<DeviceCatalogItem> findByDeviceCode(String deviceCode);
}

EligibilityCheckRecordRepository.java 
package com.example.demo.repository;

import com.example.demo.model.EligibilityCheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibilityCheckRecordRepository
        extends JpaRepository<EligibilityCheckRecord, Long> {

    List<EligibilityCheckRecord> findByEmployeeId(Long employeeId);
}

EmployeeProfileRepository.java  
package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    Optional<EmployeeProfile> findByEmail(String email);
}

IssuedDeviceRecordRepository.java  package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);

    @Query("""
        SELECT r FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.deviceItemId = :deviceItemId
          AND r.status = 'ISSUED'
    """)
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            @Param("employeeId") Long employeeId,
            @Param("deviceItemId") Long deviceItemId
    );

    @Query("""
        SELECT COUNT(r) FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.status = 'ISSUED'
    """)
    Long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);
}

PolicyRuleRepository.java  
package com.example.demo.repository;

import com.example.demo.model.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {

    Optional<PolicyRule> findByRuleCode(String ruleCode);

    List<PolicyRule> findByActiveTrue();
}

UserAccountRepository.java  package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByEmail(String email);
}