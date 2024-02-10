package com.logankells.cinemarestservice.repository;

import com.logankells.cinemarestservice.model.*;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Getter
@Repository
public class CinemaDao {

    public Cinema cinema = new Cinema(9, 9);

    public int getRows() {
        return cinema.getRows();
    }

    public int getColumns() {
        return cinema.getColumns();
    }

    public int getIncome() {
        int income = 0;
        for (Seat seat : cinema.getPurchasedSeats()) {
            income += seat.getPrice();
        }
        return income;
    }

    public List<Seat> getAvailableSeats() {
        return cinema.getSeats();
    }

    public List<Seat> getPurchasedSeats() {
        return cinema.getPurchasedSeats();
    }

    public Seat returnSeat(Ticket ticketToReturn) {
        for (Seat seat : cinema.getPurchasedSeats()) {
            if (ticketToReturn.getToken().equals(seat.getToken())) {
                cinema.getPurchasedSeats().remove(seat);
                seat.setToken("returned");
                cinema.getSeats().add(seat);
                return seat;
            }
        }
        return new Seat(-1, -1);
    }

    public boolean purchaseSeat(Seat seatToPurchase) {
        if (cinema.getSeats().contains(seatToPurchase)) {
            // Ensure proper values in purchased seat. This is necessary because
            // the no args constructor is used by Spring for the /purchase endpoint.
            seatToPurchase.setToken(UUID.randomUUID().toString());
            seatToPurchase.calculateSeatPrice();

            cinema.getPurchasedSeats().add(seatToPurchase);
            cinema.getSeats().remove(seatToPurchase);
            return true;
        } else {
            return false;
        }

    }
}
