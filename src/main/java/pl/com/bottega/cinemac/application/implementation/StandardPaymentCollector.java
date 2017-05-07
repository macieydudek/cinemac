package pl.com.bottega.cinemac.application.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;
import pl.com.bottega.cinemac.model.events.ReservationPaidByCCEvent;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.reservation.PaymentAttempt;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;

public class StandardPaymentCollector implements PaymentCollector{

    ReservationRepository reservationRepository;
    PaymentFacade paymentFacade;
    ApplicationEventPublisher applicationEventPublisher;

    public StandardPaymentCollector(ReservationRepository reservationRepository, PaymentFacade paymentFacade, ApplicationEventPublisher
            applicationEventPublisher) {
        this.reservationRepository = reservationRepository;
        this.paymentFacade = paymentFacade;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public PaymentAttempt collectPayment(CollectPaymentCommand cmd) {
        Reservation reservation = prepareReservation(cmd);
        PaymentAttempt paymentAttempt = reservation.collectPayment(cmd);
        if (cmd.getType().equals(PaymentType.CREDIT_CARD) && paymentAttempt.isSuccessful()) {
            applicationEventPublisher.publishEvent(new ReservationPaidByCCEvent(cmd.getReservationNumber()));
        }
        return paymentAttempt;
    }

    private Reservation prepareReservation(CollectPaymentCommand cmd) {
        Reservation reservation = fetchReservation(cmd);
        return reservation;
    }

    private Reservation fetchReservation(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        if (reservation == null) {
            throw new InvalidCommandException("reservationNumber", String.format("Reservation >>>%s<<< does not exist", cmd.getReservationNumber()));
        }
        return reservation;
    }
}
