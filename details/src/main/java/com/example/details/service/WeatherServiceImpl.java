package com.example.details.service;

import com.example.details.config.EndpointConfig;
import com.example.details.pojo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final EndpointConfig endpointConfig;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate, EndpointConfig endpointConfig) {
        this.restTemplate = restTemplate;
        this.endpointConfig = endpointConfig;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public WeatherResponse findCityIdByName(String city) {
        try {
            WeatherResponse response = restTemplate.getForObject(
                    endpointConfig.getWeatherUrl(city),
                    WeatherResponse.class
            );

            if (response == null) {
                log.warn("No weather data found for city: {}", city);
            }

            return response;
        } catch (Exception e) {
            log.error("Error fetching weather data for city: " + city, e);
            return null;
        }
    }
}