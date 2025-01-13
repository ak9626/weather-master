package com.example.springbootuniversitiesapi.controller;

import com.example.springbootuniversitiesapi.model.PojoB;
import com.example.springbootuniversitiesapi.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/by-countries")
    public List<PojoB> getUniversities(@RequestBody List<String> countries) {
        return universityService.getUniversitiesByMultipleCountries(countries);
    }

    @GetMapping("/")
    public List<PojoB> getUniversitiesUk() {
        List<String> countries = Arrays.asList("United Kingdom");
        return universityService.getUniversitiesByMultipleCountries(countries);
    }
}
