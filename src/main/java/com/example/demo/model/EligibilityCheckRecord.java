package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeld;
    private Long deviceltemld;
    private Boolean isEligible;
    private String reason;

    private LocalDateTime checkedAt;

    @PrePersist
    public void onCreate() {
        this.checkedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getEmployeeld() { return employeeld; }
    public void setEmployeeld(Long employeeld) { this.employeeld = employeeld; }
    public Long getDeviceltemld() { return deviceltemld; }
    public void setDeviceltemld(Long deviceltemld) { this.deviceltemld = deviceltemld; }
    public Boolean getIsEligible() { return isEligible; }
    public void setIsEligible(Boolean isEligible) { this.isEligible = isEligible; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getCheckedAt() { return checkedAt; }
}
