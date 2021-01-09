package com.example.application.data.entity;

public enum Publisher {
    MOSCOW("Москва"),
    SAINT_PETERSBURG("Санкт-Петербург"),
    O_REILLY("O’Reilly");
    private final String name;
    Publisher(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
