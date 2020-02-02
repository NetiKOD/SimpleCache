package ru.teterin;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TestTool {

    public String randomString() {
        return UUID.randomUUID().toString();
    }
}
