package com.iot.Controller;

import com.iot.AIService;
import com.iot.dto.AIChatRequest;
import com.iot.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 智能体控制器
 */
@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Validated
public class AIChatController {
    /**
     * 智能体服务
     */
    @Autowired
    private final AIService aiService;

    /**
     * 智能体通用对话
     */
    @PostMapping("/common")
    public Result<Map> CommonChat(@RequestBody @Validated AIChatRequest request) {
        return aiService.CommonChat(request.getPrompt());
    }
}
