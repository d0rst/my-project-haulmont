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

    public static Publisher fromString(String text) {
        for (Publisher b : Publisher.values()) {
            if (b.name.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
