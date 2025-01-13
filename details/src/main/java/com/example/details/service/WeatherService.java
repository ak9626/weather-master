package com.example.details.service;

import com.example.details.pojo.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {

    WeatherResponse findCityIdByName(String city);
}
