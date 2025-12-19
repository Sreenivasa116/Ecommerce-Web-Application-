//package com.application;
//
//import com.example.api.LoginApi;
//import com.example.model.LoginRequest;
//import com.example.model.LoginResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class JwtAuthenticationController implements LoginApi {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtCustomUserDetailsService jwtCustomUserDetailsService;
//    private final JwtUtils jwtUtils;
//
//    @Override
//    public ResponseEntity<LoginResponse> userLogin(LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        UserDetails userDetails = jwtCustomUserDetailsService.loadUserByUsername(
//                loginRequest.getEmail()
//        );
//
//        String token = jwtUtils.createJwtToken(null, userDetails.getUsername());
//
//        return ResponseEntity.ok(new LoginResponse("Bearer", token));
//    }
//}
