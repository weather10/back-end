package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.WeatherResponseDto;
import com.ootdgram.ootdgram.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{latitude}/{longitude}")
    public WeatherResponseDto getWeather(@PathVariable double latitude, @PathVariable double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
}
