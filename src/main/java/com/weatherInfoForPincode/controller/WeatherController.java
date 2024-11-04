package com.weatherInfoForPincode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weatherInfoForPincode.model.Weather;
import com.weatherInfoForPincode.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @GetMapping
    public ResponseEntity<?> getWeather(@RequestParam String pincode, @RequestParam String forDate) {
        try {
            Weather weather = weatherService.getWeatherInfo(pincode, forDate);
            if (weather != null) {
                return ResponseEntity.ok(weather);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}

