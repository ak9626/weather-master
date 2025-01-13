package com.example.university.controller;

import com.example.university.pojo.UniversityResponse;
import com.example.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: UniversityController
 * Package: com.example.university.controller
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 19:57
 * @version 1.0
 */
@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    // GET /universities?country=United+Kingdom
    @GetMapping
    public List<UniversityResponse> getUniversitiesByCountry(@RequestParam String country) {
        UniversityResponse[] responses = universityService.getUniversitiesByCountry(country);
        return Arrays.asList(responses);
    }

    // GET /universities/multi?countries=United+Kingdom&countries=Australia
    @GetMapping("/multi")
    public List<UniversityResponse> getUniversitiesByMultipleCountries(@RequestParam List<String> countries) {
        return universityService.getUniversitiesByCountries(countries);
    }

    // GET /universities/all
    @GetMapping("/all")
    public List<UniversityResponse> getAllUniversities() {
        UniversityResponse[] responses = universityService.getAllUniversities();
        return Arrays.asList(responses);
    }
}
