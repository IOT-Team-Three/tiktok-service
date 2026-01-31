package com.iot.Controller;

import iot.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class UserController {

    //登录
    @PostMapping("/login")
    public Result<Boolean> login() {
        return Result.success(true, "login");
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Boolean> register() {
        return Result.success(true, "register");
    }

    /**
     * 注销
     */
    @PostMapping("/logout")
    public Result<Boolean> logout() {
        return Result.success(true, "logout");
    }

    /**
     * 刷新token
     */
    @PostMapping("/refresh")
    public Result<Boolean> refresh() {
        return Result.success(true, "refresh");
    }

    /**
     * 请求CAS登录
     */
    @PostMapping("/requestCas")
    public Result<Boolean> requestCas() {
        return Result.success(true, "requestCas");
    }

    /**
     * CAS登录回调
     */
    @PostMapping("/casCallback")
    public Result<Boolean> casCallback() {
        return Result.success(true, "casCallback");
    }
}
