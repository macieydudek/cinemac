package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.showing.Seat;

import java.util.List;

public class CinemaHallDto {

    private List<Seat> free;
    private List<Seat> occupied;

    public List<Seat> getFree() {
        return free;
    }

    public void setFree(List<Seat> free) {
        this.free = free;
    }

    public List<Seat> getOccupied() {
        return occupied;
    }

    public void setOccupied(List<Seat> occupied) {
        this.occupied = occupied;
    }
}
