package com.ootdgram.ootdgram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ootdgram.ootdgram.domain.dto.ForecastWeatherResponseDto;
import com.ootdgram.ootdgram.domain.dto.WeatherInfoDto;
import com.ootdgram.ootdgram.domain.dto.WeatherResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j(topic = "OpenWeather API")
@Service
public class WeatherService {
    private final WebClient webClient;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final String API_KEY = "dad49de110da43a9f7ae26e5c1ee4973"; // OpenWeatherMap API 키
    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }
    public WeatherResponseDto getWeather(double latitude, double longitude) {
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
                .bodyToMono(WeatherResponseDto.class)
                .block();
    }
    private final String API_URL2 = "https://api.openweathermap.org/data/2.5/forecast";
    public ForecastWeatherResponseDto getForecastWeather(double latitude, double longitude) {
        String apiUrl = UriComponentsBuilder.fromUriString(API_URL2)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("lang", "kr")
                .queryParam("cnt", "24")
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .build()
                .toUriString();
        System.out.println(apiUrl);
//         JSON을 빌더 패턴을 사용하여 DTO로 변환
//        WeatherResponseDto.builder().id(4).build();
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ForecastWeatherResponseDto.class)
                .block();
    }
//    public WeatherInfoDto getWeatherInfo(double latitude, double longitude) {
//        ForecastWeatherResponseDto forecastWeatherResponseDto = getForecastWeather(latitude, longitude);
//
//        WeatherInfoDto.WeatherInfoDtoBuilder builder = WeatherInfoDto.builder();
//
//        if (forecastWeatherResponseDto != null && forecastWeatherResponseDto.getList() != null &&
//                forecastWeatherResponseDto.getList().size() > 0) {
//            ForecastWeatherResponseDto.ForecastWeatherDataDto firstForecast = forecastWeatherResponseDto.getList().get(0);
//            builder.currentTemperature(firstForecast.getMain().getTemp())
//                    .minTemperature(firstForecast.getMain().getTemp_min())
//                    .maxTemperature(firstForecast.getMain().getTemp_max())
//                    .weatherStatus(firstForecast.getWeather().get(0).getMain())
//                    .humidity(firstForecast.getMain().getHumidity());
//        }
//
//        return builder.build();
//    }
//    public WeatherInfoDto getWeatherInfo(Double latitude, Double longitude) {
//        ForecastWeatherResponseDto forecastWeatherResponseDto = getForecastWeather(latitude, longitude);
//
//        WeatherInfoDto.WeatherInfoDtoBuilder builder = WeatherInfoDto.builder();
//
//        return builder.build();
//    }



//    public CustomWeatherResponseDto getWeatherInfo(double latitude, double longitude) {
//        ForecastWeatherResponseDto forecastWeatherResponseDto = getForecastWeather(latitude, longitude);
//
//        CustomWeatherResponseDto customWeatherResponseDto = new CustomWeatherResponseDto();
//
//        if (forecastWeatherResponseDto != null && forecastWeatherResponseDto.getList() != null &&
//                forecastWeatherResponseDto.getList().size() > 0) {
//            ForecastWeatherResponseDto.ForecastWeatherDataDto firstForecast = forecastWeatherResponseDto.getList().get(0);
//            customWeatherResponseDto.setTemp(firstForecast.getMain().getTemp());
//            customWeatherResponseDto.setWeatherMain(firstForecast.getWeather().get(0).getMain());
//        }
//
//        WeatherResponseDto weatherResponseDto = getWeather(latitude, longitude);
//
//        if (weatherResponseDto != null) {
//            customWeatherResponseDto.setCityName(weatherResponseDto.getName());
//        }
//
//        return customWeatherResponseDto;
//    }

}
