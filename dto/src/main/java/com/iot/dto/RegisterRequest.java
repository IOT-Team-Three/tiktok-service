package com.iot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不可为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{1,45}$", message = "用户名不符合要求")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不可为空")
    @Pattern(regexp = "^(?![A-Z]*$)(?![a-z]*$)(?![0-9]*$)(?![!@#$%^&*()_+=\\[\\]{}|;':\",./<>?`~-]*$)[A-Za-z0-9!@#$%^&*()_+=\\[\\]{}|;':\",./<>?`~-]{5,45}$", message = "密码不符合要求")
    private String password;
}
