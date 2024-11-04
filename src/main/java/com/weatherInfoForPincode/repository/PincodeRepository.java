package com.weatherInfoForPincode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherInfoForPincode.model.Pincode;

public interface PincodeRepository extends JpaRepository<Pincode, Long> {
    Optional<Pincode> findByPincode(String pincode);
}