package com.example.springbootuniversitiesapi.service;

import com.example.springbootuniversitiesapi.model.PojoA;
import com.example.springbootuniversitiesapi.model.PojoB;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final RestTemplate restTemplate;

    public UniversityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to fetch universities by a single country
    public List<PojoB> getUniversitiesByCountry(String country) {
        String url = "http://universities.hipolabs.com/search?country=" + country;

        // Fetch data from the third-party API
        PojoB[] response = restTemplate.getForObject(url, PojoB[].class);

        // Convert array to list and return
        return response != null ? Arrays.asList(response) : Collections.emptyList();
    }

    // Method to handle multiple countries using multithreading
    public List<PojoB> getUniversitiesByMultipleCountries(List<String> countries) {
        List<CompletableFuture<List<PojoB>>> futures = countries.stream()
                .map(country -> CompletableFuture.supplyAsync(() -> getUniversitiesByCountry(country)))
                .collect(Collectors.toList());

        // Combine all results into a single list
        return futures.stream()
                .map(CompletableFuture::join) // Wait for each thread to finish
                .flatMap(List::stream)        // Flatten the list
                .collect(Collectors.toList());
    }
}
