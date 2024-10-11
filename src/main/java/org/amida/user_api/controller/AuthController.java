package org.amida.user_api.controller;

import lombok.RequiredArgsConstructor;
import org.amida.user_api.request.SignUpRequest;
import org.amida.user_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpRequest request){
        authService.register(request);
        return ResponseEntity.ok("The user registered successfully");
    }


}
