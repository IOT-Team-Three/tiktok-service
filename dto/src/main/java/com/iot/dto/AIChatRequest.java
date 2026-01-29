package com.iot.dto;

import com.iot.entity.Model;
import lombok.Data;

@Data
public class AIChatRequest {
    private String prompt;
    private Model model;
}
