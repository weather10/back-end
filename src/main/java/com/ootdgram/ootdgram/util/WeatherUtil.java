package com.ootdgram.ootdgram.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.ootdgram.ootdgram.domain.dto.WeatherResponseDto.ForecastWeatherDto;

@Slf4j(topic = "WeatherUtil")
@Component
public class WeatherUtil {// DT 변환

    public LocalDateTime convertTimestamp(Long time) {
        // Convert timestamp to LocalDateTime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.of("Asia/Seoul"));
    }

    public JsonObject stringToJsonObject(String jsonString) {
        return (JsonObject) JsonParser.parseString(jsonString);
    }

    public List<ForecastWeatherDto> getForecastWeatherDtos(JsonArray jsonArray) {
        List<ForecastWeatherDto> forecastWeatherDtos = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            // 시간
            LocalDateTime dateTime = convertTimestamp(jsonElement.getAsJsonObject().get("dt").getAsLong());
            // 온도
            double temp = jsonElement.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble();
            // 날씨
            String weather = jsonElement.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();

            forecastWeatherDtos.add(new ForecastWeatherDto(temp, weather, dateTime));
        }
        return forecastWeatherDtos;
    }
}
