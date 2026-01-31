package com.iot.Controller;

import com.iot.CommonAuthenticationService;
import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import com.iot.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class UserController {
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
