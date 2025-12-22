package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;

    private Long deviceltemId;
    private Boolean isEligible;
    private String reason;

    private LocalDateTime checkedAt;

    @PrePersist
    public void onCreate() {
        this.checkedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDeviceltemId() {
        return deviceltemId;
    }

    public void setDeviceltemId(Long deviceltemId) {
        this.deviceltemId = deviceltemId;
    }

    public Boolean getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }
}
