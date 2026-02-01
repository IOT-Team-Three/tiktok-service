package com.iot.dto;

import com.iot.model.AIModel;
import lombok.Data;

@Data
public class AIChatRequest {
    private String prompt;
    private AIModel AIModel;
}
