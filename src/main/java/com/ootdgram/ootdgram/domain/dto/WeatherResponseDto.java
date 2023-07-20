package com.ootdgram.ootdgram.domain.dto;

//import com.ootdgram.ootdgram.util.WeatherUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class WeatherResponseDto {
    // 현재 온도
    private double currentTemperature;
    // 기상상태
    private String weatherStatus;
    // 습도
    private double humidity;
    // 풍속
    private double windSpeed;
    // 주소 //Geocoding API
    private String address;
    // 강수량
    private double precipitation;
    // 날짜
    private LocalDateTime dateTime;
    // 예보 날씨 list
    private List<ForecastWeatherDto> forecastWeatherList;


    @Getter
    public static class ForecastWeatherDto {
        private double temp;
        private String weatherStatus;
        private LocalDateTime dateTime;

        public ForecastWeatherDto(double temp, String weatherStatus, LocalDateTime dateTime) {
            this.temp = temp;
            this.weatherStatus = weatherStatus;
            this.dateTime = dateTime;
        }
    }
}