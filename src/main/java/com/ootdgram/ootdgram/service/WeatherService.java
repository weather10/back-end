package com.ootdgram.ootdgram.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ootdgram.ootdgram.domain.dto.ForecastWeatherResponseDto;
import com.ootdgram.ootdgram.domain.dto.WeatherResponseDto;
import com.ootdgram.ootdgram.domain.dto.CurrentWeatherResponseDto;
import com.ootdgram.ootdgram.util.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "OpenWeather API")
@Service
public class WeatherService {
    private final WebClient webClient;

    @Value("${api.openweather.key}")
    private String API_KEY; // OpenWeatherMap API 키

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public WeatherResponseDto getWeather1(double latitude, double longitude) {
        String currnetWeatherString = getCurrentWeather(latitude, longitude);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(currnetWeatherString);
        log.info(jsonObject.getAsJsonArray("weather").toString());
        log.info("기상상태 = " + jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString());
        log.info("풍속 = " + jsonObject.getAsJsonObject("wind").get("speed").getAsDouble());
        log.info("강수량 = " + jsonObject.getAsJsonObject("rain").get("1h").getAsDouble());
        log.info("온도 = " + jsonObject.getAsJsonObject("main").get("temp").getAsDouble());
        log.info("습도 = " + jsonObject.getAsJsonObject("main").get("humidity").getAsDouble());
        log.info("dt = " + WeatherUtil.convertTimestamp(jsonObject.get("dt").getAsDouble()));
        return null;
    }

    //   test용
    public WeatherResponseDto getWeather(double latitude, double longitude) {
        String forecastWeatherString = getForecastWeather(latitude, longitude);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(forecastWeatherString);
//        log.info(jsonObject.getAsJsonArray("list").toString()); // 너무 길어서 // 현채 cnt  =24 // 3시간 간격 3일치
        JsonArray jsonArray =  jsonObject.getAsJsonArray("list");

        Map<LocalDate, List<Double>> temperatureMap = new HashMap<>();

        for (JsonElement element : jsonArray) {
            JsonObject forecastObject = element.getAsJsonObject();
            double temperature = forecastObject.getAsJsonObject("main").get("temp").getAsDouble();
            LocalDateTime dateTime = WeatherUtil.convertTimestamp(forecastObject.get("dt").getAsDouble());
            LocalDate date = dateTime.toLocalDate();

            if (!temperatureMap.containsKey(date)) {
                temperatureMap.put(date, new ArrayList<>());
            }
            temperatureMap.get(date).add(temperature);
        }

        for (LocalDate date : temperatureMap.keySet()) {
            List<Double> temperatures = temperatureMap.get(date);
            double averageTemperature = temperatures.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            log.info("날짜 = " + date);
            log.info("평균 온도 = " + String.format("%.1f", averageTemperature));
        }

        return null;
    }
    private String getForecastWeather(double latitude, double longitude) {
        String API_URL = "https://api.openweathermap.org/data/2.5/forecast";

        String apiUrl = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", "kr")
                .queryParam("cnt", "24")
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .build()
                .toUriString();

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String getCurrentWeather(double latitude, double longitude) {
        String API_URL = "https://api.openweathermap.org/data/2.5/weather";

        String apiUrl = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", "kr")
                .queryParam("appid", API_KEY)
                .queryParam("units","metric")
                .build()
                .toUriString();

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
