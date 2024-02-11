package com.logankells.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

@Data
public class Seat {

    int row;
    int column;
    int price;
    @JsonIgnore
    String token;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.token = "argsConstructor";
        calculateSeatPrice();
    }

    public Seat() {
        this.token = "noArgsConstructor";
    }

    public void calculateSeatPrice() {
        this.price = this.row <= 4 ? 10 : 8;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Seat seat = (Seat) obj;
        return this.row == seat.row && this.column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.column);
    }

}
