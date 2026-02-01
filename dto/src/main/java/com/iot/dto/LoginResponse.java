package com.iot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @Schema(description = "是否登录成功")
    private Boolean success;

    @Schema(description = "登录成功返回的token")
    private String token;

    @Schema(description = "登录成功或者失败返回的消息")
    private String message;

    @Schema(description = "登录成功返回的用户名")
    private String username;
}
