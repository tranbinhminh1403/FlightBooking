package com.example.flightbooking.repository;

import com.example.flightbooking.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
} 