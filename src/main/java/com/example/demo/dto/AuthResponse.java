// package com.example.demo.dto;

// public class AuthResponse {

//     private String token;

//     public AuthResponse() {
//     }

//     public AuthResponse(String token) {
//         this.token = token;
//     }

//     public String getToken() {
//         return token;
//     }
    
//     public void setToken(String token) {
//         this.token = token;
//     }
// }

package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private String email;
    private String role;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
