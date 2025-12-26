package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "deviceCode"))
public class DeviceCatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCode;
    private String deviceType;

    // ✅ REQUIRED BY TESTS
    private String model;

    private Integer maxAllowedPerEmployee;
    private Boolean active = true;

    /* ===== GETTERS & SETTERS ===== */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    // ✅ REQUIRED BY TESTS
    public String getModel() {
        return model;
    }

    // ✅ REQUIRED BY TESTS
    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxAllowedPerEmployee() {
        return maxAllowedPerEmployee;
    }

    public void setMaxAllowedPerEmployee(Integer maxAllowedPerEmployee) {
        this.maxAllowedPerEmployee = maxAllowedPerEmployee;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

EligibilityCheckRecord.javapackage com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceItemId;
    private Boolean isEligible;
    private String reason;
    private LocalDateTime checkedAt;

    @PrePersist
    public void prePersist() {
        this.checkedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getDeviceItemId() { return deviceItemId; }
    public void setDeviceItemId(Long deviceItemId) { this.deviceItemId = deviceItemId; }

    public Boolean getIsEligible() { return isEligible; }
    public void setIsEligible(Boolean isEligible) { this.isEligible = isEligible; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
}

EmployeeProfile.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "employee_profiles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "employeeId"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;

    private String fullName;

    private String email;

    private String department;

    private String jobRole;

    private Boolean active = true;

    private LocalDateTime createdAt;

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

IssuedDeviceRecord.javapackage com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    // ✅ REQUIRED BY TESTS
    private Long deviceItemId;

    private LocalDate issuedDate;
    private LocalDate returnedDate;
    private String status;

    /* ===== GETTERS & SETTERS ===== */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    // ✅ REQUIRED BY TESTS
    public Long getDeviceItemId() {
        return deviceItemId;
    }

    // ✅ REQUIRED BY TESTS
    public void setDeviceItemId(Long deviceItemId) {
        this.deviceItemId = deviceItemId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

PolicyRule.javapackage com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "policy_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;

    private String description;

    private String appliesToRole;

    private String appliesToDepartment;

    private Integer maxDevicesAllowed;

    private Boolean active = true;

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppliesToRole() {
        return appliesToRole;
    }

    public void setAppliesToRole(String appliesToRole) {
        this.appliesToRole = appliesToRole;
    }

    public String getAppliesToDepartment() {
        return appliesToDepartment;
    }

    public void setAppliesToDepartment(String appliesToDepartment) {
        this.appliesToDepartment = appliesToDepartment;
    }

    public Integer getMaxDevicesAllowed() {
        return maxDevicesAllowed;
    }

    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

UserAccount.javapackage com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}