package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CinemaHall {

    private static final Integer ROWS = 10;
    private static final Integer SEATS = 15;

    private boolean[][] seats;

    public CinemaHall(Set<Reservation> reservations) {
        seats = new boolean[ROWS][SEATS];
        createSeatsFromReservations(reservations);
    }

    private void createSeatsFromReservations(Set<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            for (Seat seat : reservation.getSeats())
                seats[seat.getRow()][seat.getSeat()] = true;
        }
    }

    public List<Seat> getFreeSeats() {
        List<Seat> freeSeats = new LinkedList<>();
        for (int row = 0; row < ROWS; row++)
            for (int num = 0; num < SEATS; num++)
                if (!seats[row][num])
                    freeSeats.add(new Seat(row + 1, num + 1));
        return freeSeats;
    }

    public List<Seat> getOccupiedSeats() {
        List<Seat> occupiedSeats = new LinkedList<>();
        for (int row = 0; row < ROWS; row++)
            for (int num = 0; num < SEATS; num++)
                if (seats[row][num])
                    occupiedSeats.add(new Seat(row + 1, num + 1));
        return occupiedSeats;
    }

    public boolean isPossible(CreateReservationCommand cmd) {
        boolean[][] seatsCopy = copySeats();

        Set<Seat> reservationSeats = cmd.getSeats();
        for (Seat seat : reservationSeats) {
            if (this.seats[seat.getRow()][seat.getSeat()])
                return false;
            else
                seatsCopy[seat.getRow()][seat.getSeat()] = true;
        }

        int reservationRow = reservationSeats.iterator().next().getRow();
        return rowIsOk(seatsCopy, reservationRow) ||
                !otherRowsAreFree(reservationSeats, seatsCopy);

    }

    private boolean otherRowsAreFree(Set<Seat> seats, boolean[][] seatsCopy) {
        for (int r = 0; r < ROWS; r++) {
            int seatsCount = 0;
            for (int s = 0; s < SEATS; s++) {
                if (seatsCount == seats.size()) {
                    for (int i = 0; i <= seatsCount; i++) {
                        seatsCopy[r][s - i] = true;
                    }

                    if (rowIsOk(seatsCopy, r))
                        return true;

                    seatsCount = 0;
                }
                if (!seatsCopy[r][s])
                    seatsCount++;
                else
                    seatsCount = 0;
            }
        }
        return false;
    }

    private boolean rowIsOk(boolean[][] seatsCopy, int row) {
        if (!seatsCopy[row][0] && seatsCopy[row][1])
            return false;
        if (!seatsCopy[row][14] && seatsCopy[row][13])
            return false;
        for (int num = 1; num < SEATS; num++) {
            if (!seatsCopy[row][num] && seatsCopy[row][num - 1] && seatsCopy[row][num + 1])
                return false;
        }
        return true;
    }

    private boolean[][] copySeats() {
        boolean[][] seatsCopy = new boolean[ROWS][SEATS];

        for (int row = 0; row < ROWS; row++)
            for (int num = 0; num < SEATS; num++)
                seatsCopy[row][num] = this.seats[row][num];
        return seatsCopy;
    }
}