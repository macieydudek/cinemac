package pl.com.bottega.cinemac.model.showing;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {

    private Integer row;
    private Integer seat;

    Seat(){}

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

    @Override
    public String toString() {
        return  "row: " + row +
                " seat: " + seat;
    }
}