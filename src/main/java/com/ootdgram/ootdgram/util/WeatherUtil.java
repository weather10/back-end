package com.ootdgram.ootdgram.util;

import com.ootdgram.ootdgram.domain.dto.ForecastWeatherResponseDto;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class WeatherUtil {// DT 변환
    public static LocalDateTime convertTimestamp(Double timestamp) {
        // Convert timestamp to LocalDateTime
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.longValue()), ZoneId.of("Asia/Seoul"));
    }
//    public static double tempAvg(List<ForecastWeatherResponseDto.ForecastWeatherDataDto> list) {
//    }
//
//    public static String getWeather(List<ForecastWeatherResponseDto.ForecastWeatherDataDto> list) {
//
//    }
//
//    public static LocalDateTime getDateTime(List<ForecastWeatherResponseDto.ForecastWeatherDataDto> list) {
//
//    }


}
