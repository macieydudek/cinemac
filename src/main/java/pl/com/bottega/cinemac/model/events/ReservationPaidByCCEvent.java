package pl.com.bottega.cinemac.model.events;


import pl.com.bottega.cinemac.model.reservation.ReservationNumber;

public class ReservationPaidByCCEvent {


    private ReservationNumber reservationNumber;

    public ReservationPaidByCCEvent(ReservationNumber reservationNumber) {

        this.reservationNumber = reservationNumber;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }
}
