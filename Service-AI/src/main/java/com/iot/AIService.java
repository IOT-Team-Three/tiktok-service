package com.iot;

import com.iot.dto.Result;

import java.util.Map;

public interface AIService {

    Result<Map> CommonChat(String prompt);
}
