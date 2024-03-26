package ru.vladp.models;

import lombok.Getter;

@Getter
public enum BrowsersSupport {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String browserName;

    BrowsersSupport(String browserName) {
        this.browserName = browserName;
    }
}

