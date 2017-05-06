package pl.com.bottega.cinemac.model.reservation;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;


@Embeddable
public class ReservationNumber implements Serializable{

    private String reservationNumber;

    public ReservationNumber() {
        this.reservationNumber = UUID.randomUUID().toString();
    }

    public String getReservationNumber() {
        return reservationNumber;
    }
}
