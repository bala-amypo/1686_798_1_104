// package com.example.demo.entity;

// import java.time.LocalDate;
// import jakarta.persistence.*;

// @Entity
// @Table(name = "issued_device_records")
// public class IssuedDeviceRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long employeeId;

//     private Long deviceItemId;

//     private LocalDate issuedDate;

//     private LocalDate returnedDate;

//     private String status;

//     @PrePersist
//     public void onIssue() {
//         this.issuedDate = LocalDate.now();
//         this.status = "ISSUED";
//     }

//     @PreUpdate
//     public void onReturn() {
//         if (this.returnedDate != null) {
//             this.status = "RETURNED";
//         }
//     }

 
//     public Long getId() {
//          return id; 
//          }
//     public Long getEmployeeId() { 
//         return employeeId;
//          }
//     public void setEmployeeId(Long employeeId) {
//          this.employeeId = employeeId;
//           }
//     public Long getDeviceItemId() { 
//     return deviceItemId; 
//     }
//     public void setDeviceItemId(Long deviceItemId) { 
//         this.deviceItemId = deviceItemId; 
//         }
//     public LocalDate getIssuedDate() {
//          return issuedDate; }
//     public LocalDate getReturnedDate(){ 
//         return returnedDate;
//      }
//     public void setReturnedDate(LocalDate returnedDate) {
//          this.returnedDate = returnedDate; 
//          }
//     public String getStatus() {
//          return status; 
//          }
}
