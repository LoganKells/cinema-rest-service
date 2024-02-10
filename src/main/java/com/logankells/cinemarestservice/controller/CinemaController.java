package com.logankells.cinemarestservice.controller;

import com.logankells.cinemarestservice.model.*;
import com.logankells.cinemarestservice.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {

    CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/test")
    public int returnOne() {
        return 1;
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinemaService.getCinema();
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<String> postPurchaseSeat(@RequestBody Seat seat) {
        return cinemaService.purchaseSeat(seat);
    }

    @PostMapping(value = "/return")
    public ResponseEntity<String> postReturnSeat(@RequestBody Ticket ticket) {
        return cinemaService.returnSeat(ticket);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<String> getStats(@RequestParam(value = "password", required = false) String password) {
        return cinemaService.getStats(password);
    }
}
