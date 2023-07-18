package com.ootdgram.ootdgram.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherInfoDto {
    private double currentTemperature;
    private double minTemperature;
    private double maxTemperature;
    private String weatherStatus;
    private double humidity;

}
