package com.ootdgram.ootdgram.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
//@Builder
public class CurrentWeatherResponseDto {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private long visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private long timezone;
    private long id;
    private String name;
    private long cod;

    // Constructors, getters, setters, etc.
    @Getter
    public static class Coord {
        private double lon;
        private double lat;
    }
    @Getter
    public static class Weather {
        private long id;
        private String main;
        private String description;
        private String icon;
    }
    @Getter
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private long pressure;
        private long humidity;
    }

    @Getter
    public static class Wind {
        private double speed;
        private long deg;
    }
    @Getter
    public static class Rain {
        private double h1;
    }
    @Getter

    public static class Clouds {
        private long all;
    }

    @Getter
    public static class Sys {
        private long type;
        private long id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
