package com.example.search.client.details;

import com.example.common.domain.GeneralResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: DetailsClientFallbackFactory
 * Package: com.example.search.client
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 17:43
 * @version 1.0
 */

@Slf4j
@Component
public class DetailsClientFallbackFactory implements FallbackFactory<DetailsClient> {
    @Override
    public DetailsClient create(Throwable throwable) {
        return new DetailsClient() {
            @Override
            public GeneralResponse getWeatherByCity(String city) {
                log.error("Failed to get weather data for city: {}, reason: {}",
                        city, throwable.getMessage());
                GeneralResponse response = new GeneralResponse();
                response.setCode(500);
                response.setTimestamp(new Date());
                response.setData(null);
                return response;
            }

            @Override
            public GeneralResponse getPort() {
                log.error("Failed to get details service port, reason: {}",
                        throwable.getMessage());
                GeneralResponse response = new GeneralResponse();
                response.setCode(500);
                response.setTimestamp(new Date());
                response.setData("Details service unavailable");
                return response;
            }
        };
    }
}