// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import jakarta.persistence.*;

// @Entity
// @Table(name = "eligibility_check_records")
// public class EligibilityCheckRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long employeeId;

//     private Long deviceItemId;

//     private Boolean isEligible;

//     private String reason;

//     private LocalDateTime checkedAt;

//     @PrePersist
//     public void onCheck() {
//         this.checkedAt = LocalDateTime.now();
//     }

  
//     public Long getId(){
//          return id; 
//          }
//     public Long getEmployeeId() { 
//         return employeeId; 
//         }
//     public void setEmployeeId(Long employeeId) {
//          this.employeeId = employeeId; 
//          }
//     public Long getDeviceItemId() {
//          return deviceItemId;
//           }
//     public void setDeviceItemId(Long deviceItemId) { 
//         this.deviceItemId = deviceItemId;
//          }
//     public Boolean getIsEligible() {
//          return isEligible; 
//          }
//     public void setIsEligible(Boolean isEligible) {
//          this.isEligible = isEligible;
//           }
//     public String getReason() { 
//         return reason; 
//         }
//     public void setReason(String reason) { 
//         this.reason = reason;
//          }
//     public LocalDateTime getCheckedAt() { 
//         return checkedAt; 
//         }
// }


. Dependency Injection:
You must use Constructor Injection for all Service classes. Do not use Field Injection
(@Autowired on fields). The test suite instantiates services manually using
constructors.

.. Custom Exceptions:
Create com.example.demo.exception.BadRequestException (used for validation errors).
Create com.example.demo.exception.ResourceNotFoundException (used for missing
entities).

. Exception Messages:

When throwing BadRequestException, your message must contain these keywords:
Duplicate Employee ID: "Employeeld already exists"
Invalid Max Limit: "maxAllowedPerEmployee"
Device Already Returned: "already returned"
Duplicate Rule Code: "Rule code"
. Repository Method Signatures (Exact Naming Required):
EmployeeProfileRepository: findByEmployeeld(String employeeld)
· DeviceCatalogltemRepository: findByDeviceCode(String deviceCode)
IssuedDeviceRecordRepository: countActiveDevicesForEmployee(Long employeeld)
IssuedDeviceRecordRepository: findActiveByEmployeeAndDevice(Long employeeld,
Long deviceltemld)
. PolicyRuleRepository: findByActiveTrue()
. PolicyRuleRepository: findByRuleCode(String ruleCode)
EligibilityCheckRecordRepository: findByEmployeeld(Long employeeld)

2. DeviceCatalogltem

. Fields: id (Long, PK), deviceCode (String), deviceType (String), model (String),
maxAllowedPerEmployee (Integer), active (Boolean)
Rules:

deviceCode must be unique.
maxAllowedPerEmployee ≥ 1 (Throw BadRequestException if invalid).
2. DeviceCatalogService

· createltem(DeviceCatalogltem item)
. updateActiveStatus(Long id, boolean active)
. getAllltems()
2. DeviceCatalogController (/api/devices)

· POST / - Create device catalog item
. PUT /{id}/active - Activate/deactivate item
. GET / - List all device items
