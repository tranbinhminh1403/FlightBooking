package com.example.flightbooking.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long bookingId;
    private FlightTicketDTO flight;
    private LocalDateTime bookingDate;
    private String status;
    private BigDecimal totalPrice;
    private String paymentStatus;
} 