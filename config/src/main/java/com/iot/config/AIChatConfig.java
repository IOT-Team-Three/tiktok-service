package com.iot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AIChatConfig {

    final OllamaChatModel model;

    @Bean
    public ChatClient chatClient() {
        return ChatClient.builder(model)
                .defaultSystem("你是一个视频软件的小助手，名叫 prts")
                .build();
    }
}
