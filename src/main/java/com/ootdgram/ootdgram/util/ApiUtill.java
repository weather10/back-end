package com.ootdgram.ootdgram.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j(topic = "ApiCall")
@Component
public class ApiUtill {
    private final WebClient webClient;

    @Value("${api.openweather.key}")
    private String API_KEY;

    @Value("${api.geo.key}")
    private String GEO_API_KEY;

    public ApiUtill(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getForecastWeather(double latitude, double longitude) {
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

    public String getCurrentWeather(double latitude, double longitude) {
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

    public String geocoding(double latitude, double longitude) {

        String API_URL = "http://api.vworld.kr/req/address";

        String apiUrl = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("service", "address")
                .queryParam("request", "getAddress")
                .queryParam("version", "2.0")
                .queryParam("crs", "epsg:4326")
                .queryParam("point", longitude + "," + latitude)
                .queryParam("format", "json")
                .queryParam("type", "both")
                .queryParam("zipcode", "true")
                .queryParam("simple", "false")
                .queryParam("key", GEO_API_KEY)
                .build()
                .toUriString();
        log.info(apiUrl);
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
