package com.iot.Impl;

import com.iot.CommonAuthenticationService;
import com.iot.dto.Result;
import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommonAuthenticationServiceImpl implements CommonAuthenticationService {

    @Override
    public Result<LoginResponse> login(LoginRequest loginRequest) {
        return Result.success(new LoginResponse());
    }

    @Override
    public Result<String> register(RegisterRequest registerRequest) {
        return Result.success("register");
    }
}
