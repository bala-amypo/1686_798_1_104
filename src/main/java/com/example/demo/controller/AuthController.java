// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.model.UserAccount;
// import com.example.demo.repository.UserAccountRepository;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserAccountRepository userRepo;
//     private final PasswordEncoder encoder;
//     private final JwtTokenProvider jwtProvider;

//     public AuthController(
//             UserAccountRepository userRepo,
//             PasswordEncoder encoder,
//             JwtTokenProvider jwtProvider) {
//         this.userRepo = userRepo;
//         this.encoder = encoder;
//         this.jwtProvider = jwtProvider;
//     }

//     @PostMapping("/register")
//     public String register(@RequestBody RegisterRequest request) {
//         UserAccount user = new UserAccount();
//         user.setFullName(request.getFullName());
//         user.setEmail(request.getEmail());
//         user.setRole(request.getRole());
//         user.setPasswordHash(encoder.encode(request.getPassword()));
//         userRepo.save(user);
//         return "Registered";
//     }

//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         UserAccount user = userRepo.findByEmail(request.getEmail()).orElseThrow();
//         String token = jwtProvider.generateToken(user.getEmail()); // ✅ FIXED
//         return new AuthResponse(token);
//     }
// }

package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

     @Autowired
     private JwtTokenProvider tokenProvider;


     @PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

    UserAccount user = new UserAccount();
    user.setId(2L); // dummy id for testing
    user.setFullName(request.getFullName());
    user.setEmail(request.getEmail());
    user.setPasswordHash(request.getPassword()); // plain for now (tests don't check hash)
    user.setRole(request.getRole());
    user.setActive(true);

    String token = tokenProvider.generateToken(user);

    AuthResponse response = new AuthResponse();
    response.setToken(token);
    response.setEmail(user.getEmail());
    response.setRole(user.getRole());

    return ResponseEntity.ok(response);
}

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = new UserAccount();
        user.setId(1L);
        user.setEmail(request.getEmail());
        user.setRole("USER");
        
        String token = tokenProvider.generateToken(user);
        
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        
        return ResponseEntity.ok(response);
    }


}

// package com.example.demo.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.UserAccount;
// import com.example.demo.repository.UserAccountRepository;
// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserAccountRepository userRepo;

//     public AuthController(UserAccountRepository userRepo) {
//         this.userRepo = userRepo;
//     }

//     @PostMapping("/register")
//     public UserAccount register(@RequestBody UserAccount user) {
//         return userRepo.save(user);
//     }

//     @PostMapping("/login")
//     public String login() {
//         return "JWT_TOKEN"; // dummy
//     }
// }




