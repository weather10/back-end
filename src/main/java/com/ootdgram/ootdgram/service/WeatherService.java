package com.ootdgram.ootdgram.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ootdgram.ootdgram.domain.dto.WeatherResponseDto;
import com.ootdgram.ootdgram.util.ApiUtill;
import com.ootdgram.ootdgram.util.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.ootdgram.ootdgram.domain.dto.WeatherResponseDto.*;

@Slf4j(topic = "OpenWeather API")
@Service
public class WeatherService {
    private final WebClient webClient;
    private final WeatherUtil weatherUtil;
    private final ApiUtill apiUtill;

    public WeatherService(WebClient webClient, WeatherUtil weatherUtil, ApiUtill apiUtill) {
        this.webClient = webClient;
        this.weatherUtil = weatherUtil;
        this.apiUtill = apiUtill;
    }
    public WeatherResponseDto getWeather(double latitude, double longitude) {
        String forecastWeatherString = apiUtill.getForecastWeather(latitude, longitude);
        String currentWeather = apiUtill.getCurrentWeather(latitude, longitude);
        String address = getGeo(latitude, longitude);

        // 날씨 예보 관련
        JsonObject forecastWeatherJsonObject = weatherUtil.stringToJsonObject(forecastWeatherString);
        JsonArray jsonArray = forecastWeatherJsonObject.getAsJsonArray("list");
        List<ForecastWeatherDto> forecastWeatherDtos = weatherUtil.getForecastWeatherDtos(jsonArray);

        // 현재 날시 관련
        JsonObject currentWeatherJsonObject = weatherUtil.stringToJsonObject(currentWeather);
        JsonElement main = currentWeatherJsonObject.getAsJsonObject().get("main");
        double temp = main.getAsJsonObject().get("temp").getAsDouble();
        int humidity = main.getAsJsonObject().get("humidity").getAsInt();
        String weatherStatus = currentWeatherJsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        double windSpeed = currentWeatherJsonObject.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble();

        LocalDateTime dateTime = weatherUtil.convertTimestamp(currentWeatherJsonObject.getAsJsonObject().get("dt").getAsLong());
        double precipitation = 0.0;
        if (currentWeatherJsonObject.has("rain")) {
            precipitation = currentWeatherJsonObject.getAsJsonObject("rain").get("1h").getAsDouble();
        }

        return WeatherResponseDto.builder()
                .forecastWeatherList(forecastWeatherDtos)
                .currentTemperature(temp)
                .weatherStatus(weatherStatus)
                .windSpeed(windSpeed)
                .dateTime(dateTime)
                .precipitation(precipitation)
                .humidity(humidity)
                .address(address)
                .build();
    }

    public String getGeo(double latitude, double longitude) {
        String geoString = apiUtill.geocoding(latitude, longitude);
        JsonObject jsonObject = weatherUtil.stringToJsonObject(geoString).getAsJsonObject();
        JsonObject response = jsonObject.getAsJsonObject("response");
        JsonArray results = response.getAsJsonArray("result");
        JsonObject firstResult = results.get(0).getAsJsonObject();
        String address = firstResult.get("text").getAsString();
        log.info("Address: " + address);
        return address;
    }
}
