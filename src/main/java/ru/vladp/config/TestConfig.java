package ru.vladp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public record TestConfig(
        @JsonProperty("site.url") String site,
        @JsonProperty("wait-timeout") String waitTimeout,
        @JsonProperty("chrome-version") String chromeVersion,
        @JsonProperty("firefox-version") String firefoxVersion

) {
    static final String SITE_URL;
    static final String WAIT_TIMEOUT;
    static final String CHROME_VERSION;
    static final String FIREFOX_VERSION;

    static {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            TestConfig config = objectMapper.readValue(
                    new File("src/main/resources/application.yml"),
                    TestConfig.class
            );
            System.out.println("Application config info " + config.toString());

            SITE_URL = config.site();
            WAIT_TIMEOUT = config.waitTimeout();
            CHROME_VERSION = config.chromeVersion();
            FIREFOX_VERSION = config.firefoxVersion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}