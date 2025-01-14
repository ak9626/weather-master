package com.example.details.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EndpointConfig {
    @Value("${weather.api.key}")
    private String apiKey;

    private static final String BASE_URL = "http://api.weatherapi.com/v1";

    public String getWeatherUrl(String city) {
        return String.format("%s/current.json?key=%s&q=%s&aqi=no",
                BASE_URL, apiKey, city);
    }
}