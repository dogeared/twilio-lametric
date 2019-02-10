package com.afitnerd.twilio_lametric.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("#{ @environment['base.url'] }")
    private String baseUrl;

    public static final String API_PATH = "/api/v1";

    public String getBaseUrl() {
        return baseUrl;
    }
}
