package com.example.details.controller;

import com.example.details.pojo.WeatherResponse;
import com.example.details.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RefreshScope
@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    private final WeatherService weatherService;

    @Value("${server.port}")
    private int randomServerPort;

    @GetMapping
    public WeatherResponse queryWeatherByCity(@RequestParam String city) {
        log.info("Received query for city: {}", city);

        WeatherResponse weatherData = weatherService.findCityIdByName(city);
        if (weatherData == null) {
            log.warn("No weather data found for city: {}", city);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City weather data not found");
        }
        return weatherData;
    }

    @GetMapping("/port")
    public String queryServicePort() {
        return "weather service + " + randomServerPort;
    }

}