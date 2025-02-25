package com.example.flightbooking.controller;

import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    
    @Autowired
    private FlightRepository flightRepository;
    
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }
} 