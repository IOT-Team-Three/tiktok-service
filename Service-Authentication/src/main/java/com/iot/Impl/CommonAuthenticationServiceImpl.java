package com.iot.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.CommonAuthenticationService;
import com.iot.FastMethodConfig;
import com.iot.JwtUtilsConfig;
import com.iot.dto.Result;
import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import com.iot.entity.User;
import com.iot.mapper.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommonAuthenticationServiceImpl implements CommonAuthenticationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FastMethodConfig fastMethodConfig;

    @Autowired
    private JwtUtilsConfig jwtUtilsConfig;

    @Override
    public Result<LoginResponse> login(LoginRequest loginRequest) {
        try {
            // 验证用户密码
            User user = (User) userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, loginRequest.getUsername())
                    .eq(User::getPassword, fastMethodConfig.md5(loginRequest.getPassword())));
            if (user == null) {
                return Result.error("用户名或密码错误");
            }
            // 登录成功，返回登录成功信息
            return Result.success(new LoginResponse(true, jwtUtilsConfig.generateToken(user.getUserid().intValue(), user.getUsername(), user.getPassword()), "登录成功", user.getUsername()));
        } catch (Exception e) {
            return Result.error("登录失败");
        }
    }

    @Override
    public Result<String> register(RegisterRequest registerRequest) {
        try {
            // 验证用户名是否已存在
            if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, registerRequest.getUsername())) != null) {
                return Result.error("用户名已存在");
            }
            // 注册用户
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(fastMethodConfig.md5(registerRequest.getPassword()));
            userMapper.insert(user);
            // 注册成功，返回注册成功信息
            return Result.success(jwtUtilsConfig.generateToken(user.getUserid().intValue(), user.getUsername(), user.getPassword()), "注册成功");
        } catch (Exception e) {
            return Result.error("注册失败");
        }
    }
}
