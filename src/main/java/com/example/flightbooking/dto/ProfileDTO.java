package com.example.flightbooking.dto;

import lombok.Data;
import java.util.List;


@Data
public class ProfileDTO {
    private Long userId;
    private String username;
    private String email;
    private List<BookingDTO> bookings;
} 