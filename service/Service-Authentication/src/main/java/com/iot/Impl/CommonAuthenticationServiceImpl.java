package com.iot.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iot.CommonAuthenticationService;
import com.iot.config.FastMethodConfig;
import com.iot.config.JwtUtilsConfig;
import com.iot.dto.Result;
import com.iot.dto.LoginRequest;
import com.iot.dto.LoginResponse;
import com.iot.dto.RegisterRequest;
import com.iot.entity.User;
import com.iot.mapper.UserMapper;
import jakarta.servlet.http.HttpServletResponse;
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
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", loginRequest.username);
            queryWrapper.eq("password", fastMethodConfig.md5(loginRequest.password));
            User user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                return Result.error(HttpServletResponse.SC_OK,"用户名或密码错误");
            }
            // 登录成功，返回登录成功信息
            return Result.success(new LoginResponse(true, jwtUtilsConfig.generateToken(user.userid.intValue(), user.username, user.password), "登录成功", user.username));
        } catch (Exception e) {
            return Result.error(HttpServletResponse.SC_OK,"登录失败");
        }
    }

    @Override
    public Result<String> register(RegisterRequest registerRequest) {
        try {
            // 验证用户名是否已存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", registerRequest.username);
            if (userMapper.selectOne(queryWrapper) != null) {
                return Result.error(HttpServletResponse.SC_OK,"用户名已存在");
            }
            // 注册用户
            User user = new User();
            user.username = registerRequest.username;
            user.password = fastMethodConfig.md5(registerRequest.password);
            userMapper.insert(user);
            // 注册成功，返回注册成功信息
            return Result.success(jwtUtilsConfig.generateToken(user.userid.intValue(), user.username, user.password), "注册成功");
        } catch (Exception e) {
            return Result.error(HttpServletResponse.SC_OK,"注册失败");
        }
    }
}
