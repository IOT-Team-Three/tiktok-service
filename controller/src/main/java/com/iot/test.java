package com.iot;

import com.iot.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试后端
 */
@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
@Validated
public class test {

    @GetMapping("/test")
    public Result<String> getTest() {
        return Result.success("Test OK!");
    }
}