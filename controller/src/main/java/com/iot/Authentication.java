package com.iot;

import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class Authentication {

    @Autowired
    private CommonAuthenticationService commonAuthenticationService;

    @GetMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return commonAuthenticationService.login(loginRequest);
    }

    @GetMapping("/register")
    public Result<String> register(@RequestBody RegisterRequest registerRequest) {
        return commonAuthenticationService.register(registerRequest);
    }
}
