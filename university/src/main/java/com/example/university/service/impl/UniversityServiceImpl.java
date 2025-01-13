package com.example.university.service.impl;

import com.example.university.exception.ThirdPartyApiException;
import com.example.university.pojo.UniversityResponse;
import com.example.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * ClassName: UniversityServiceImpl
 * Package: com.example.university.service.impl
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:57
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityServiceImpl implements UniversityService {
    private final RestTemplate restTemplate;

    @Override
    public UniversityResponse[] getUniversitiesByCountry(String country) {
        String url = "http://universities.hipolabs.com/search?country=" + country;

        try {
            log.debug("Fetching universities for country: {}", country);
            UniversityResponse[] response = restTemplate.getForObject(url, UniversityResponse[].class);

            if (response == null) {
                log.warn("No universities found for country: {}", country);
                response = new UniversityResponse[0];
            }

            return response;
        } catch (Exception e) {
            log.error("Failed to fetch universities for country: {}", country, e);
            throw new ThirdPartyApiException("Failed to get universities data for country: " + country, e);
        }
    }

    @Override
    // Use CompletableFuture to implement parallel requests
    public List<UniversityResponse> getUniversitiesByCountries(List<String> countries) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            List<CompletableFuture<UniversityResponse[]>> futures = countries.stream()
                    .map(country -> CompletableFuture.supplyAsync(() -> getUniversitiesByCountry(country), executor))
                    .collect(Collectors.toList());

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            return futures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

        } finally {
            executor.shutdown();
        }
    }

    @Override
    public UniversityResponse[] getAllUniversities() {
        String url = "http://universities.hipolabs.com/search";

        try {
            UniversityResponse[] response = restTemplate.getForObject(url, UniversityResponse[].class);
            if (response == null) {
                response = new UniversityResponse[0];
            }
            return response;
        } catch (Exception e) {
            throw new ThirdPartyApiException("Failed to get all universities data", e);
        }
    }

}
