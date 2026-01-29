package com.iot.entity;

import lombok.Getter;

@Getter
public enum Model {
    Deepseek("deepseek"),
    Doubao("doubao");

    private final String model;
    Model(String model) {
        this.model = model;
    }
}
