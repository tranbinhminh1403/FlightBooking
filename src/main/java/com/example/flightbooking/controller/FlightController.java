package com.example.flightbooking.controller;

import com.example.flightbooking.dto.SearchFlightRequest;
import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.flightbooking.dto.FlightTicketDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

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

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam(required = false) String departureAirport,
            @RequestParam(required = false) String arrivalAirport,
            @RequestParam(required = false) String departureTimeStart,
            @RequestParam(required = false) String departureTimeEnd,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String airlineName) {
        LocalDateTime startDateTime = departureTimeStart != null ? LocalDateTime.parse(departureTimeStart) : null;
        LocalDateTime endDateTime = departureTimeEnd != null ? LocalDateTime.parse(departureTimeEnd) : null;
        return flightRepository.searchFlights(
            departureAirport, 
            arrivalAirport,
            startDateTime,
            endDateTime,
            null,  // arrivalTimeStart
            null,  // arrivalTimeEnd
            status,
            airlineName
        );
    }

    @PostMapping("/search")
    public List<Flight> searchFlightsPost(@RequestBody SearchFlightRequest request) {
        return flightRepository.searchFlights(
            request.getDepartureAirport(),
            request.getArrivalAirport(),
            request.getDepartureTimeStart(),
            request.getDepartureTimeEnd(),
            request.getArrivalTimeStart(),
            request.getArrivalTimeEnd(),
            request.getStatus(),
            request.getAirlineName()
        );
    }

    @GetMapping("/tickets/search")
    public List<FlightTicketDTO> searchFlightTickets(
            @RequestParam(required = false) String departureAirport,
            @RequestParam(required = false) String arrivalAirport,
            @RequestParam(required = false) String departureTimeStart,
            @RequestParam(required = false) String departureTimeEnd,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String airlineName) {
        LocalDateTime startDateTime = departureTimeStart != null ? LocalDateTime.parse(departureTimeStart) : null;
        LocalDateTime endDateTime = departureTimeEnd != null ? LocalDateTime.parse(departureTimeEnd) : null;
        List<FlightTicketDTO> tickets = flightRepository.searchFlightTickets(
            departureAirport, 
            arrivalAirport,
            startDateTime,
            endDateTime,
            status,
            airlineName
        );
        
        // Set default pricing for each flight
        for (FlightTicketDTO ticket : tickets) {
            ticket.setEconomyPrice(new BigDecimal("299.99"));
            ticket.setBusinessPrice(new BigDecimal("899.99"));
            ticket.setFirstClassPrice(new BigDecimal("1499.99"));
            ticket.setAvailableSeats(180); // Default available seats
        }
        
        return tickets;
    }
} 