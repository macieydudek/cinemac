package pl.com.bottega.cinemac.model.commands;


import pl.com.bottega.cinemac.model.ReservationNumber;

public class CollectPaymentCommand {
    private ReservationNumber reservationNumber;

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }
}
