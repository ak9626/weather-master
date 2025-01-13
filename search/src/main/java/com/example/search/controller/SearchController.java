package com.example.search.controller;

import com.example.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public Map<String, Object> getAggregatedData(
            @RequestParam String country,
            @RequestParam String city) {
        log.info("Fetching aggregated data for country: {} and city: {}",
                country, city);
        return searchService.getAggregatedData(country, city);
    }

    @GetMapping("/universities")
    public Object getUniversityData(@RequestParam String country) {
        log.info("Fetching university data for country: {}", country);
        return searchService.getUniversityData(country);
    }

    @GetMapping("/details")
    public Object getWeatherData(@RequestParam String city) {
        log.info("Fetching weather data for city: {}", city);
        return searchService.getWeatherData(city);
    }

    @GetMapping("/port")
    public String getServicePort() {
        log.info("Fetching service port information");
        return searchService.getServicePort();
    }
}