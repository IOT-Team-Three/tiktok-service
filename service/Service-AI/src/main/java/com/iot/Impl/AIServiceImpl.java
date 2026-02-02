package com.iot.Impl;

import com.iot.AIService;
import com.iot.dto.Result;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AIServiceImpl implements AIService {

    @Autowired
    private ChatClient chatClient;

    @Override
    public Result<Map> CommonChat(String prompt) {
        String content = chatClient.prompt()
                .user(prompt)
                .call()
                .content();
        if (content == null || content.isEmpty())
            return Result.error("AI回复为空");
        return Result.success(Map.of("content", content));
    }
}
