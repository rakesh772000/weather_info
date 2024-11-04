package com.weatherInfoForPincode.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String pincode;
    private double latitude;
    private double longitude;

    // Getters and Setters
}

