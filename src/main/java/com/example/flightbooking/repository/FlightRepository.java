package com.example.flightbooking.repository;

import com.example.flightbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
} 