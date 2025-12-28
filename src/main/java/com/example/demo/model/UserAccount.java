// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// @Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// public class UserAccount {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;
//     private String email;
//     private String passwordHash;
//     private String role;
//     private Boolean active = true;

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getFullName() { return fullName; }
//     public void setFullName(String fullName) { this.fullName = fullName; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPasswordHash() { return passwordHash; }
//     public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
// }

// package com.example.demo.model;
// import jakarta.persistence.*;

// @Entity
// @Table(name = "user_accounts")
// public class UserAccount {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(unique = true)
//     private String email;
    
//     private String password;
//     private String role;

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
    
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
    
//     public String getRole() { return role; }
//     public void setRole(String role) { this.role = role; }
// }

// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "user_account")
// public class UserAccount {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true)
//     private String email;

//     private String passwordHash;

//     private String role; // ADMIN / IT_OPERATOR / AUDITOR

//     private Boolean active;

//     public UserAccount() {
//     }

//     public UserAccount(String fullName, String email,
//                        String passwordHash, String role,
//                        Boolean active) {
//         this.fullName = fullName;
//         this.email = email;
//         this.passwordHash = passwordHash;
//         this.role = role;
//         this.active = active;
//     }

//     // getters and setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPasswordHash() {
//         return passwordHash;
//     }

//     public void setPasswordHash(String passwordHash) {
//         this.passwordHash = passwordHash;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     public Boolean getActive() {
//         return active;
//     }

//     public void setActive(Boolean active) {
//         this.active = active;
//     }
// }

package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    // IMPORTANT: tests + spec expect hashed password
    @Column(nullable = false)
    private String password;

    // ADMIN / IT_OPERATOR / AUDITOR
    private String role;

    private Boolean active = true;

    // ---------- getters & setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
