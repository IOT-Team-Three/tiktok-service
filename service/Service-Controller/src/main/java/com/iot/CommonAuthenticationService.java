package com.iot;

import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public interface CommonAuthenticationService {

    /**
     * 登录
     *
     * @param loginRequest 登录请求参数
     * @return 登录响应参数
     */
    @Schema(description = "登录")
    Result<LoginResponse> login(LoginRequest loginRequest);

    /**
     * 注册
     *
     * @param registerRequest 注册请求参数
     * @return 注册响应参数
     */
    @Schema(description = "注册")
    Result<String> register(RegisterRequest registerRequest);
}
