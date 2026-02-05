package com.iot.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ResourceConfig {
    /**
     * 上传路径
     */
    private final String UPLOAD_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\upload\\";

    /**
     * 视频路径
     */
    private final String VIDEO_PATH = UPLOAD_PATH+"video\\";
}
