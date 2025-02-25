package com.example.flightbooking.controller;

import com.example.flightbooking.entity.Airline;
import com.example.flightbooking.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {
    
    @Autowired
    private AirlineRepository airlineRepository;
    
    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }
} 