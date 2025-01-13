package com.example.search.service;

import com.example.common.domain.GeneralResponse;
import com.example.search.client.details.DetailsClient;
import com.example.search.client.university.UniversityClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SearchServiceImpl
 * Package: com.example.search.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 17:36
 * @version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final UniversityClient universityClient;
    private final DetailsClient detailsClient;
    private final ObjectMapper objectMapper;

    @Override
    @HystrixCommand(fallbackMethod = "getAggregatedDataFallback")
    public Map<String, Object> getAggregatedData(String country, String city) {
        CompletableFuture<Object> universityFuture = CompletableFuture.supplyAsync(() ->
                getUniversityData(country));

        CompletableFuture<Object> weatherFuture = CompletableFuture.supplyAsync(() ->
                getWeatherData(city));

        CompletableFuture<String> serviceDetailsFuture = CompletableFuture.supplyAsync(
                this::getServicePort);

        try {
            Map<String, Object> result = new HashMap<>();
            result.put("universities", universityFuture.get(5, TimeUnit.SECONDS));
            result.put("weather", weatherFuture.get(5, TimeUnit.SECONDS));
            result.put("serviceDetails", serviceDetailsFuture.get(5, TimeUnit.SECONDS));
            return result;
        } catch (Exception e) {
            log.error("Error aggregating data for country: {} and city: {}",
                    country, city, e);
            throw new RuntimeException("Failed to aggregate data");
        }
    }

    public Map<String, Object> getAggregatedDataFallback(String country, String city) {
        Map<String, Object> fallback = new HashMap<>();
        fallback.put("universities", Collections.emptyList());
        fallback.put("weather", null);
        fallback.put("serviceDetails", "Service temporarily unavailable");
        return fallback;
    }

    @Override
    public Object getUniversityData(String country) {
        GeneralResponse response = universityClient.getUniversitiesByCountry(country);
        return response.getData();
    }

    @Override
    public Object getWeatherData(String city) {
        GeneralResponse response = detailsClient.getWeatherByCity(city);
        return response.getData();
    }

    @Override
    public String getServicePort() {
        GeneralResponse response = detailsClient.getPort();
        return response.getData().toString();
    }
}