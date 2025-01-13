package com.example.search.client.university;

import com.example.common.domain.GeneralResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: UniversityClient
 * Package: com.example.search.client
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 20:59
 * @version 1.0
 */
@FeignClient(
        name = "university",
        fallbackFactory = UniversityClientFallbackFactory.class
)
public interface UniversityClient {
    @GetMapping("/universities")
    GeneralResponse getUniversitiesByCountry(@RequestParam String country);
}