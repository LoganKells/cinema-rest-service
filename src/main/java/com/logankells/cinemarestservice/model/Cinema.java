package com.logankells.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Setter
@Getter
public class Cinema {

    int rows;
    int columns;
    List<Seat> seats; // Seats available to purchase
    @JsonIgnore
    List<Seat> purchasedSeats;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = Collections.synchronizedList(new ArrayList<>());
        this.purchasedSeats = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                Seat newSeat = new Seat(i, j);
                this.seats.add(newSeat);
            }
        }
    }
}
