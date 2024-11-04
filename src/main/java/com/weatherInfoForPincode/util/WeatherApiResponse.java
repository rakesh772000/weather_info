package com.weatherInfoForPincode.util;


import java.util.List;

import lombok.Data;
@Data
public class WeatherApiResponse {
    private Main main;
    private List<Weather> weather;

  
    @Data
    public static class Main {
        private double temp;
        private int humidity;

 
    }
     @Data
    public static class Weather {
        private String description;

   
    }
}

