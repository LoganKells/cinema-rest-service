package com.logankells.cinemarestservice.controller;

import com.logankells.cinemarestservice.model.Cinema;
import com.logankells.cinemarestservice.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return cinemaService.cinema;
    }
}
