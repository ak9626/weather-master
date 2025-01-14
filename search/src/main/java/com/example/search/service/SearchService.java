package com.example.search.service;

import java.util.Map;

/**
 * ClassName: SearchService
 * Package: com.example.search.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 17:35
 * @version 1.0
 */
public interface SearchService {
    /**
     * Get weather and university aggregated data by country and city
     * @param country
     * @param city
     * @return Aggregated data
     */
    Map<String, Object> getAggregatedData(String country, String city);

    /**
     * Get university data by country
     * @param country
     * @return University data
     */
    Object getUniversityData(String country);

    /**
     * Get weather data by city
     * @param city
     * @return Weather data
     */
    Object getWeatherData(String city);

    /**
     * Get details service port information
     * @return Details service port
     */
    String getServicePort();
}
