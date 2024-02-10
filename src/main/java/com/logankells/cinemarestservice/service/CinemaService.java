package com.logankells.cinemarestservice.service;

import com.logankells.cinemarestservice.model.*;
import com.logankells.cinemarestservice.repository.CinemaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    CinemaDao cinemaDao;

    @Autowired
    public CinemaService(CinemaDao cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    public Cinema getCinema() {
        return cinemaDao.getCinema();
    }

    public ResponseEntity<String> getStats(String password) {
        if (!"super_secret".equals(password)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("{\"error\": \"The password is wrong!\"}");
        }
        int income = cinemaDao.getIncome();
        int availableSeatCount = cinemaDao.getAvailableSeats().size();
        int purchasedSeatCount = cinemaDao.getPurchasedSeats().size();
        return ResponseEntity.ok().body(
                String.format("{\"income\": %s, " +
                                "\"available\": %s, " +
                                "\"purchased\": %s}",
                        income, availableSeatCount, purchasedSeatCount));
    }

    public ResponseEntity<String> returnSeat(Ticket ticketToReturn) {
        Seat seatReturned = cinemaDao.returnSeat(ticketToReturn);
        if (seatReturned.getRow() == -1) {
            return ResponseEntity.badRequest().body("{\"error\": \"Wrong token!\"}");
        }
        return ResponseEntity.ok().body(
                String.format("{\"ticket\": " +
                                "{\"row\": %s, " +
                                "\"column\": %s, " +
                                "\"price\": %s}}",
                        seatReturned.getRow(), seatReturned.getColumn(),
                        seatReturned.getPrice()));
    }

    public ResponseEntity<String> purchaseSeat(Seat seatToPurchase) {
        if (seatToPurchase.getRow() > cinemaDao.getRows()
                || seatToPurchase.getRow() < 1
                || seatToPurchase.getColumn() < 1
                || seatToPurchase.getColumn() > cinemaDao.getColumns()) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"The number of a row or a column is out of bounds!\"}");
        }

        // Purchase a ticket
        boolean seatPurchased = cinemaDao.purchaseSeat(seatToPurchase);
        if (seatPurchased) {
            return ResponseEntity.ok().body(
                    String.format("{\"token\": \"%s\", " +
                                    "\"ticket\": " +
                                    "{\"row\": %s, " +
                                    "\"column\": %s, " +
                                    "\"price\": %s}}",
                            seatToPurchase.getToken(), seatToPurchase.getRow(),
                            seatToPurchase.getColumn(), seatToPurchase.getPrice()));
        } else {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"The ticket has been already purchased!\"}");
        }
    }
}
