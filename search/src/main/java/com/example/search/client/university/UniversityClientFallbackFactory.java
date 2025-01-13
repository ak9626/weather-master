package com.example.search.client.university;

import com.example.common.domain.GeneralResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

/**
 * ClassName: UniversityClientFallbackFactory
 * Package: com.example.search.client
 * Description:
 *
 * @author Fan Peng
 * Create 2025/1/12 20:59
 * @version 1.0
 */
@Slf4j
@Component
public class UniversityClientFallbackFactory implements FallbackFactory<UniversityClient> {
    @Override
    public UniversityClient create(Throwable throwable) {
        return new UniversityClient() {
            @Override
            public GeneralResponse getUniversitiesByCountry(String country) {
                log.error("Failed to get universities for country: {}, reason: {}",
                        country, throwable.getMessage());
                GeneralResponse response = new GeneralResponse();
                response.setCode(500);
                response.setTimestamp(new Date());
                response.setData(Collections.emptyList());
                return response;
            }
        };
    }
}