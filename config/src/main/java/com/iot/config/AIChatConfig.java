package com.iot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIChatConfig {

    @Bean
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient.builder(model)
                .defaultSystem("你是一个视频软件的小助手，名叫 prts")
                .build();
    }
}
