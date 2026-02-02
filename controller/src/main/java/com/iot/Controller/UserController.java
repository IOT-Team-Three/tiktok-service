package com.iot.Controller;

import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import com.iot.dto.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口
 */
@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class UserController {

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return Result.success(new LoginResponse("mock-token", "mock-username"));
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterRequest registerRequest) {
        return Result.success("User registered successfully");
    }
}
