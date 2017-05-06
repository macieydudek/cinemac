package pl.com.bottega.cinemac.model.commands;


import pl.com.bottega.cinemac.model.PaymentType;
import pl.com.bottega.cinemac.model.ReservationNumber;

public class CollectPaymentCommand {
    private ReservationNumber reservationNumber;
    private PaymentType type;

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public PaymentType getType() {
        return type;
    }
}
