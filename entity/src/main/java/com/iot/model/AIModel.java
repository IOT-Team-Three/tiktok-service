package com.iot.model;

import lombok.Getter;

@Getter
public enum AIModel {
    Deepseek("deepseek"),
    Doubao("doubao");

    private final String model;
    AIModel(String model) {
        this.model = model;
    }
}
