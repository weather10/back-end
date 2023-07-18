package com.ootdgram.ootdgram.controller;

import com.ootdgram.ootdgram.domain.dto.ForecastWeatherResponseDto;
import com.ootdgram.ootdgram.domain.dto.WeatherInfoDto;
import com.ootdgram.ootdgram.domain.dto.WeatherResponseDto;
import com.ootdgram.ootdgram.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/currentWeather/{latitude}/{longitude}")
    public WeatherResponseDto getWeather(@PathVariable double latitude, @PathVariable double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
    @GetMapping("/forecastWeather/{latitude}/{longitude}")
    public ForecastWeatherResponseDto getForecastWeather(@PathVariable double latitude, @PathVariable double longitude) {
        return weatherService.getForecastWeather(latitude, longitude);
    }
//    @GetMapping("/weaterInfo/{latitude}/{longitude}")
//    public WeatherInfoDto getWeatherInfo(double latitude, double longitude) {
//        WeatherInfoDto weatherInfo = weatherService.getWeatherInfo(latitude, longitude);
//        return weatherService.getWeatherInfo(latitude,longitude);
//    }

}
