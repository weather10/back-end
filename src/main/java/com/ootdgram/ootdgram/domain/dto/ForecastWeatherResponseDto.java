package com.ootdgram.ootdgram.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
//@Builder
public class ForecastWeatherResponseDto {
    private String cod;
    private int message;
    private int cnt;
    private List<ForecastWeatherDataDto> list;
    private CityDto city;

    // Constructor, getters, and setters

    @Getter
    public static class ForecastWeatherDataDto {
        private long dt;
        private MainDataDto main;
        private List<WeatherDto> weather;
        private CloudsDto clouds;
        private WindDto wind;
        private int visibility;
        private int pop;
        private SysDto sys;
        private String dt_txt;

        // Constructor, getters, and setters
    }

    @Getter
    public static class MainDataDto {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int sea_level;
        private int grnd_level;
        private int humidity;
        private double temp_kf;
    }

    @Getter
    public static class WeatherDto {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Getter
    public static class CityDto {
        private int id;
        private String name;
        private CoordDto coord;
        private String country;
        private int population;
        private int timezone;
        private long sunrise;
        private long sunset;
    }

    @Getter
    public static class CoordDto {
        private double lat;
        private double lon;
    }

    @Getter
    public static class CloudsDto {
        private int all;
    }

    @Getter
    public static class WindDto {
        private double speed;
        private int deg;
        private double gust;
    }

    @Getter
    public static class SysDto {
        private String pod;
    }
}
