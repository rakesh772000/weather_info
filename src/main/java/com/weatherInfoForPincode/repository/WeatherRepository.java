package com.weatherInfoForPincode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherInfoForPincode.model.Pincode;
import com.weatherInfoForPincode.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByPincodeAndDate(Pincode pincode, String date);
}
