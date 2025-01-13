package com.example.university.service;

import com.example.university.pojo.UniversityResponse;

import java.util.List;

/**
 * ClassName: UniversityService
 * Package: com.example.university.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:57
 * @version 1.0
 */
public interface UniversityService {
    UniversityResponse[] getUniversitiesByCountry(String country);
    List<UniversityResponse> getUniversitiesByCountries(List<String> countries);
    UniversityResponse[] getAllUniversities();
}
