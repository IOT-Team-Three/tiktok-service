package com.iot;

import com.iot.dto.Result;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public interface AIService {

    /**
     * 通用聊天
     *
     * @param prompt 聊天内容
     * @return 聊天结果
     */
    @Schema(description = "通用聊天")
    Result<Map> CommonChat(String prompt);
}
