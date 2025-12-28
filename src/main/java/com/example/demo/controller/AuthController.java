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

// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.UserAccount;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//      @Autowired
//      private JwtTokenProvider tokenProvider;


//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//         UserAccount user = new UserAccount();
//         user.setId(1L);
//         user.setEmail(request.getEmail());
//         user.setRole("USER");
        
//         String token = tokenProvider.generateToken(user);
        
//         AuthResponse response = new AuthResponse();
//         response.setToken(token);
//         response.setEmail(user.getEmail());
//         response.setRole(user.getRole());
        
//         return ResponseEntity.ok(response);
//     }


// }

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



package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserAccountRepository userRepo;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserAccountRepository userRepo,
                          JwtTokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.tokenProvider = tokenProvider;
    }

    // ---------- LOGIN REQUEST (NO LOMBOK) ----------
    static class LoginRequest {
        private String email;
        private String password;

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

    // ---------- REGISTER ----------
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Duplicate email");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole("IT_OPERATOR");
        }

        user.setActive(true);

        return userRepo.save(user);
    }

    // ---------- LOGIN ----------
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        String token = tokenProvider.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return response;
    }
}
