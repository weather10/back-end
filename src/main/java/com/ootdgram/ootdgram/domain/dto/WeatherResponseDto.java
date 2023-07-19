package com.ootdgram.ootdgram.domain.dto;

//import com.ootdgram.ootdgram.util.WeatherUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class WeatherResponseDto {//강수량, 풍속1, 습도1, 주소, 온도1, 날짜1 ,기상상태1
    // 현재 온도
    private double currentTemperature;
    // 기상상태
    private String weatherStatus;
    // 습도
    private double humidity;
    // 풍속
    private double windSpeed;
    // 주소 //Geocoding API?//
    private String address;
    // 강수량 // 현재날씨에는 1h 단위, 예보날씨에는 3h 단위?? 통일 해줘야하나.  //!!!!! 문제 발생  비안올때는 강수량 필드가 없이 응답해서 에러발생!
    /*
        if (jsonObject.has("rain")) {
            log.info("강수량 = " + jsonObject.getAsJsonObject("rain").get("1h").getAsDouble());
        } else {
            log.info("강수량 정보가 없습니다.");
        } 추가해야함
        확인후 삭제*/ // 넹
    private double precipitation;
    // 날짜
    private LocalDateTime dateTime;
    // 예보 날씨 list
    private List<ForecastWeatherDto> forecastWeatherList;


    @Getter
    public static class ForecastWeatherDto {
        private double temp;
        private String weatherStatus;
        private LocalDateTime dateTime;

        public ForecastWeatherDto(double temp, String weatherStatus, LocalDateTime dateTime) {
            this.temp = temp;
            this.weatherStatus = weatherStatus;
            this.dateTime = dateTime;
        }
    }
}

//        public forecastWeather(ForecastWeatherResponseDto fo) {
//            this.forecastTempAvg = WeatherUtil.tempAvg(fo.getList());
//            this.weatherStatus = WeatherUtil.getWeather(fo.getList());
//            this.dateTime = WeatherUtil.getDateTime(fo.getList());
//        }
//    }

// 넘겨줘야하는 dto 데이터, 표시되는 데이터는 현재날씨 종합 , 3일? 5일? 날씨 예보 // 넘길때 한번에 넘기되, 현재와 예보 날씨를 둘다 넘겨서, 어떻게 담을지??
//
//    {
//    currentTemperature: ㅁㅁㅁ,
//    weatherStatus: ㅁㅁㅁ,
//    weatherStatus: ㅁㅁㅁ,
//    humidity: ㅁㅁㅁ,
//    windSpeed: ㅁㅁㅁ,
//    address: ㅁㅁㅁ,
//    precipitation: ㅁㅁㅁ,
//    dateTime: ㅁㅁㅁ
//    list{  // 예보날씨 리스트
//          forecastTempAvg :aa,
//          weatherStatus : aaa,
//          dateTime:
//         }
//    }
//    public WeatherResponseDto(CurrentWeatherResponseDto currentWeather,
//                              ForecastWeatherResponseDto forecastWeather) {
//        this.currentTemperature = currentWeather.getMain().getTemp();
//        this.weatherStatus = currentWeather.getWeather().get(0).getMain();
//        this.humidity = currentWeather.getMain().getHumidity();
//        this.windSpeed = currentWeather.getWind().getSpeed();
//        this.address = null;
//        this.precipitation = currentWeather.getRain().getH1();
//        this.dateTime = WeatherUtil.convertTimestamp(currentWeather.getDt());
//        this.forecastWeatherList =
