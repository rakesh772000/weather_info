package com.weatherInfoForPincode.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pincode_id")
    private Pincode pincode;
    
    private String date;
    private double temperature;
    private double humidity;
    private String description;

    // Getters and Setters
}
