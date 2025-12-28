// package com.example.demo.dto;

// public class AuthRequest {

//     private String email;
//     private String password;

//     public AuthRequest() {
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }
    
//     public void setPassword(String password) {
//         this.password = password;
//     }
// }

// package com.example.demo.dto;

// public class AuthRequest {
//     private String email;
//     private String password;

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
    
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
// }

// package com.example.demo.dto;

// public class AuthRequest {

//     private String email;
//     private String password;

//     public AuthRequest() {}

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }
// }

package com.example.demo.dto;

public class AuthRequest {

    private String fullName;   // register
    private String email;      // register + login
    private String password;   // register
    private String role;       // register

    public AuthRequest() {
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
}
