package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.CurrentWeatherResponseDto;
import com.ootdgram.ootdgram.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{latitude}/{longitude}")
    public CurrentWeatherResponseDto getWeather(@PathVariable double latitude, @PathVariable double longitude) {
        weatherService.getWeather(latitude, longitude);
        return new CurrentWeatherResponseDto();
    }
}
