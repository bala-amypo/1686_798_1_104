// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(
//     name = "user_accounts",
//     uniqueConstraints = @UniqueConstraint(columnNames = "email")
// )
// public class UserAccount {
//     @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String passwordHash;

//     private String role; // ADMIN / IT_OPERATOR / AUDITOR

//     private Boolean active = true;


//     public Long getId() {
//          return id; 
//          }
//     public String getFullName() { 
//         return fullName;
//          }
//     public void setFullName(String fullName) { 
//         this.fullName = fullName;
//          }
//     public String getEmail() {
//      return email; 
//      }
//     public void setEmail(String email) {
//      this.email = email; 
//      }
//     public String getPasswordHash() {
//          return passwordHash; 
//          }
//     public void setPasswordHash(String passwordHash) { 
//         this.passwordHash = passwordHash;
//          }
//     public String getRole() {
//          return role; 
//          }
//     public void setRole(String role) {
//          this.role = role;
//           }
//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
}


