package pl.com.bottega.cinemac.model.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.showing.Seat;
import pl.com.bottega.cinemac.model.showing.Showing;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Reservation {

    @Autowired
    @Transient
    PriceCalculator priceCalculator;

    @ManyToOne
    private Showing showing;
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
    private Set<PaymentAttempt> paymentAttempts;

    Reservation() {

    }

    public Reservation(CreateReservationCommand cmd, Showing showing) {
        this.showing = showing;
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
            return payByCC(cmd);
        } else
            return payByCash(cmd);

    }

    private void preparePaymentHistory() {
        if (this.paymentAttempts == null) {
            this.paymentAttempts = new HashSet<>();
        }
    }

    private PaymentAttempt payByCC(CollectPaymentCommand cmd) {
        PaymentAttempt paymentAttempt = PaymentAttempt.createCCPaymentAttempt(paymentFacade.charge(cmd.getCreditCard(), cmd.getAmount()));
        updateStatus(paymentAttempt);
        paymentAttempts.add(paymentAttempt);
        return paymentAttempt;
    }

    private PaymentAttempt payByCash(CollectPaymentCommand cmd) {
        PaymentAttempt paymentAttempt = PaymentAttempt.createCashPaymentAttempt(cmd.getCashierId());
        updateStatus(paymentAttempt);
        paymentAttempts.add(paymentAttempt);
        return paymentAttempt;
    }

    private void updateStatus(PaymentAttempt paymentAttempt) {
        if (paymentAttempt.isSuccessful()) {
            this.status = ReservationStatus.PAID;
        } else {
            this.status = ReservationStatus.PAYMENT_FAILED;
        }
    }

    private void checkStatus() {
        if (!(this.status.equals(ReservationStatus.PENDING) || this.status.equals(ReservationStatus.PAYMENT_FAILED))) {
            throw new InvalidUserActionException("Reservation has to be PENDING OR PAYMENT_FAILED");
        }
    }

    public void setPaymentFacade(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    public Showing getShowing() {
        return showing;
    }
}
