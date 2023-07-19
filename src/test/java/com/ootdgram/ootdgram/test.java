//private CurrentWeatherResponseDto getCurrentWeather(double latitude, double longitude) {
//        String API_URL = "https://api.openweathermap.org/data/2.5/weather";
//
//        String apiUrl = UriComponentsBuilder.fromUriString(API_URL)
//        .queryParam("lat", latitude)
//        .queryParam("lon", longitude)
//        .queryParam("lang", "kr")
//        .queryParam("appid", API_KEY)
//        .queryParam("units","metric")
//        .build()
//        .toUriString();
//
//        return webClient.get()
//        .uri(apiUrl)
//        .retrieve()
//        .bodyToMono(JsonObject.class)
//        .map(json -> {
//        CurrentWeatherResponseDto weatherResponse = new CurrentWeatherResponseDto();
//        weatherResponse.setTemperature(json.getAsJsonObject("main").get("temp").getAsDouble());
//        weatherResponse.setHumidity(json.getAsJsonObject("main").get("humidity").getAsInt());
//        // Parse other necessary data from the JSON response and set it in weatherResponse
//        // ...
//
//        return weatherResponse;
//        })
//        .block();
//        }
//
//private ForecastWeatherResponseDto getForecastWeather(double latitude, double longitude) {
//        String API_URL = "https://api.openweathermap.org/data/2.5/forecast";
//
//        String apiUrl = UriComponentsBuilder.fromUriString(API_URL)
//        .queryParam("lat", latitude)
//        .queryParam("lon", longitude)
//        .queryParam("lang", "kr")
//        .queryParam("cnt", "24")
//        .queryParam("appid", API_KEY)
//        .queryParam("units", "metric")
//        .build()
//        .toUriString();
//
//        return webClient.get()
//        .uri(apiUrl)
//        .retrieve()
//        .bodyToMono(JsonObject.class)
//        .map(json -> {
//        ForecastWeatherResponseDto weatherResponse = new ForecastWeatherResponseDto();
//        // Parse the necessary data from the JSON response and set it in weatherResponse
//        // ...
//
//        return weatherResponse;
//        })
//        .block();
//        }
