package com.weatherInfoForPincode.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.weatherInfoForPincode.model.Pincode;
import com.weatherInfoForPincode.model.Weather;
import com.weatherInfoForPincode.repository.PincodeRepository;
import com.weatherInfoForPincode.repository.WeatherRepository;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {
    @Mock
    private PincodeRepository pincodeRepository;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    public WeatherServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherInfo_existingPincode() {
        // Setup test data
        Pincode pincode = new Pincode();
        pincode.setPincode("411014");
        pincode.setLatitude(18.567);
        pincode.setLongitude(73.756);
        Weather weather = new Weather();
        weather.setTemperature(30.0);
        weather.setHumidity(60);
        weather.setDescription("Clear Sky");

        when(pincodeRepository.findByPincode("411014")).thenReturn(Optional.of(pincode));
        when(weatherRepository.findByPincodeAndDate(pincode, "2020-10-15")).thenReturn(Optional.of(weather));

        // Call the method
        Weather result = weatherService.getWeatherInfo("411014", "2020-10-15");

        // Verify and assert
        assertNotNull(result);
        assertEquals("Clear Sky", result.getDescription());
    }
}
