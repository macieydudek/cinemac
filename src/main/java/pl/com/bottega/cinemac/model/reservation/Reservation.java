package pl.com.bottega.cinemac.model.reservation;

import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.showing.Seat;

import javax.persistence.*;
import java.util.HashSet;
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
    @Transient
    private PaymentFacade paymentFacade;
    @ElementCollection
    private Set<PaymentAttempt> paymentHistory;

    Reservation(){

    }

    public Reservation(CreateReservationCommand cmd){
        this.showId = cmd.getShowId();
        this.customer = cmd.getCustomer();
        this.reservationItems = cmd.getTickets();
        this.seats = cmd.getSeats();
        this.reservationNumber = new ReservationNumber();
        this.status = ReservationStatus.PENDING;
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

    public PaymentAttempt collectPayment(CollectPaymentCommand cmd) {
        checkStatus();
        preparePaymentHistory();
        if (cmd.getType().equals(PaymentType.CREDIT_CARD)) {
            payByCC(cmd);
        } else {
            return payByCash(cmd);
        }
        return null;
    }

    private void preparePaymentHistory() {
        if (this.paymentHistory == null) {
            this.paymentHistory = new HashSet<>();
        }
    }

    private void payByCC(CollectPaymentCommand cmd) {
    }

    private PaymentAttempt payByCash(CollectPaymentCommand cmd) {
        this.status = ReservationStatus.PAID;
        PaymentAttempt paymentAttempt = new PaymentAttempt(cmd.getType(), cmd.getCashierId());
        paymentHistory.add(paymentAttempt);
        return paymentAttempt;
    }

    private void checkStatus() {
        if (!(this.status.equals(ReservationStatus.PENDING) || this.status.equals(ReservationStatus.PAYMENT_FAILED))) {
            throw new InvalidUserActionException("Reservation has to be PENDING OR PAYMENT_FAILED");
        }
    }

    public void setPaymentFacade(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    public Long getShowId() {
        return showId;
    }
}
