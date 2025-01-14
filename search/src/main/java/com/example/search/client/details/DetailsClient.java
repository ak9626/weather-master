package com.example.search.client.details;

import com.example.common.domain.GeneralResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: DetailsClient
 * Package: com.example.search.client
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 17:33
 * @version 1.0
 */

@FeignClient(
        name = "details",
        fallbackFactory = DetailsClientFallbackFactory.class
)
public interface DetailsClient {
    @GetMapping("/details")
    GeneralResponse getWeatherByCity(@RequestParam String city);

    @GetMapping("/details/port")
    GeneralResponse getPort();
}