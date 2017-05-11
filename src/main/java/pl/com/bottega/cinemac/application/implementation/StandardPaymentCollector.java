package pl.com.bottega.cinemac.application.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;
import pl.com.bottega.cinemac.model.events.ReservationPaidByCCEvent;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.reservation.PaymentAttempt;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;
import pl.com.bottega.cinemac.model.reservation.ReservationStatus;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;

import java.math.BigDecimal;

public class StandardPaymentCollector implements PaymentCollector {

    ReservationRepository reservationRepository;
    PaymentFacade paymentFacade;
    ApplicationEventPublisher applicationEventPublisher;
    ShowingRepository showingRepository;
    PriceCalculator priceCalculator;

    public StandardPaymentCollector(ReservationRepository reservationRepository, PaymentFacade paymentFacade, ApplicationEventPublisher
            applicationEventPublisher, ShowingRepository showingRepository, PriceCalculator priceCalculator) {
        this.reservationRepository = reservationRepository;
        this.paymentFacade = paymentFacade;
        this.applicationEventPublisher = applicationEventPublisher;
        this.showingRepository = showingRepository;
        this.priceCalculator = priceCalculator;
    }

    @Override
    @Transactional
    public PaymentAttempt collectPayment(CollectPaymentCommand cmd) {
        if (cmd.getType().equals(PaymentType.CREDIT_CARD)) {
            return collectPaymentByCC(cmd);
        } else {
            return collectPaymentByCash(cmd);
        }
    }

    private PaymentAttempt collectPaymentByCash(CollectPaymentCommand cmd) {
        Reservation reservation = prepareReservation(cmd);
        return reservation.collectPayment(cmd);
    }

    private PaymentAttempt collectPaymentByCC(CollectPaymentCommand cmd) {
        Reservation reservation = prepareReservation(cmd);
        cmd.setAmount(getAmount(reservation));
        PaymentAttempt paymentAttempt = reservation.collectPayment(cmd);
        propagatePublishEvent(reservation);
        return paymentAttempt;
    }

    private void propagatePublishEvent(Reservation reservation) {
        if (reservation.getStatus().equals(ReservationStatus.PAID)) {
            applicationEventPublisher.publishEvent(new ReservationPaidByCCEvent(reservation.getReservationNumber()));
        }
    }

    private BigDecimal getAmount(Reservation reservation) {
        CalculatePriceCommand calculateCmd = new CalculatePriceCommand();
        calculateCmd.setShowId(reservation.getShowId());
        calculateCmd.setTickets(reservation.getReservationItems());
        return priceCalculator.calculatePrice(calculateCmd).getTotalPrice();
    }

    private Reservation prepareReservation(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        if (reservation == null) {
            throw new InvalidCommandException("reservationNumber", String.format("Reservation >>>%s<<< does not exist", cmd.getReservationNumber()));
        }
        return reservation;
    }
}
