package com.iot.Controller;

import com.iot.CommonAuthenticationService;
import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import com.iot.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证接口
 */
@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class UserController {

    @Autowired
    private CommonAuthenticationService authenticationService;

    /**
     * 登录
     *
     * @param loginRequest 登录请求参数
     * @return 登录响应参数
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    /**
     * 注册
     *
     * @param registerRequest 注册请求参数
     * @return 注册响应参数
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }
}
