package com.iot;

import java.util.Map;

public interface AIService {

    Result<Map> generate(String prompt);
}
