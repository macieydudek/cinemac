package pl.com.bottega.cinemac.model;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

    private Integer row;
    private Integer seat;

    public Seat(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getSeat() {
        return seat;
    }
}
