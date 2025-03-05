package com.example.flightbooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private String role;
    private String initialAirport;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
} 