Dto  AuthRequest.java   package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {
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
}

AuthResponse.java  package com.example.demo.dto;

public class AuthResponse {

    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}

IssuedDeviceRequest.java  package com.example.demo.dto;

public class IssuedDeviceRequest {

    private Long employeeId;
    private Long deviceItemId;

    public IssuedDeviceRequest() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDeviceItemId() {
        return deviceItemId;
    }
    
    public void setDeviceItemId(Long deviceItemId) {
        this.deviceItemId = deviceItemId;
    }
}

  RegisterRequest.java  package com.example.demo.dto;

public class RegisterRequest {

    private String fullName;
    private String email;
    private String password;
    private String role;

    public RegisterRequest() {
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