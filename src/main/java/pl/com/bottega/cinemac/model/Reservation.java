package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Reservation {

    private Long showId;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @EmbeddedId
    private ReservationNumber reservationNumber;
    @Embedded
    private Customer customer;
    @ElementCollection
    private Set<ReservationItem> reservationItems;
    @ElementCollection
    private Set<Seat> seats;


    Reservation(){

    }

    public Reservation(CreateReservationCommand cmd){
        this.showId = cmd.getShowId();
        this.status = cmd.getStatus();
        this.customer = cmd.getCustomer();
        this.reservationItems = cmd.getTickets();
        this.seats = cmd.getSeats();
        this.reservationNumber = new ReservationNumber();
    }


    public ReservationStatus getStatus() {
        return status;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void collectPayment(CollectPaymentCommand cmd) {

    }
}
