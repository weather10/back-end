package com.ootdgram.ootdgram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
                .queryParam("appid", API_KEY)
                .build()
                .toUriString();

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(WeatherResponseDto.class)
                .block();
    }
}
//import com.fasterxml.jackson.core.JsonProcessingException;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import com.ootdgram.ootdgram.domain.dto.WeatherResponse;
//        import lombok.extern.slf4j.Slf4j;
//        import org.springframework.http.MediaType;
//        import org.springframework.stereotype.Service;
//        import org.springframework.web.reactive.function.client.WebClient;
//
//@Slf4j(topic = "OpenWeather API")
//@Service
//public class Weather {
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
//    private static final String API_KEY = "dad49de110da43a9f7ae26e5c1ee4973"; // OpenWeatherMap API 키
////
////    public WeatherService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
////        this.webClient = webClientBuilder.build();
////        this.objectMapper = objectMapper;
////    }
////
////    public WeatherResponse getWeather(double latitude, double longitude) {
////        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&lang=kr&appid=" + API_KEY;
////        log.info("Request URL: {}", url);
////
////        WeatherResponse weatherResponse = webClient.get().uri(url)
////                .accept(MediaType.APPLICATION_JSON)
////                .retrieve()
////                .bodyToMono(WeatherResponse.class)
////                .block();
////
////        if (weatherResponse != null) {
////            try {
////                String json = objectMapper.writeValueAsString(weatherResponse);
////                log.info("Weather JSON: {}", json);
////            } catch (JsonProcessingException e) {
////                log.error("Error converting WeatherResponse to JSON", e);
////            }
////        }
////
////        return weatherResponse;
////    }
//}
