package com.example.demo.repository;

import com.example.demo.model.DeviceCatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DeviceCatalogItemRepository extends JpaRepository<DeviceCatalogItem, Long> {
    Optional<DeviceCatalogItem> findByDeviceCode(String deviceCode);
}


package com.example.demo.repository;

import com.example.demo.model.EligibilityCheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EligibilityCheckRecordRepository extends JpaRepository<EligibilityCheckRecord, Long> {
    List<EligibilityCheckRecord> findByEmployeeId(Long employeeId);
}

package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
    Optional<EmployeeProfile> findByEmail(String email);
}


package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {
    
    @Query("SELECT COUNT(i) FROM IssuedDeviceRecord i WHERE i.employeeId = :employeeId AND i.status = 'ISSUED'")
    Long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);
    
    @Query("SELECT i FROM IssuedDeviceRecord i WHERE i.employeeId = :employeeId AND i.deviceItemId = :deviceItemId AND i.status = 'ISSUED'")
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(@Param("employeeId") Long employeeId, @Param("deviceItemId") Long deviceItemId);
}

package com.example.demo.repository;

import com.example.demo.model.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {
    Optional<PolicyRule> findByRuleCode(String ruleCode);
    List<PolicyRule> findByActiveTrue();
}

package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}