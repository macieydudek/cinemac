package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.model.PaymentType;
import pl.com.bottega.cinemac.model.Reservation;
import pl.com.bottega.cinemac.model.ReservationRepository;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;

public class StandardPaymentCollector implements PaymentCollector{

    ReservationRepository reservationRepository;

    public StandardPaymentCollector(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void collectPayment(CollectPaymentCommand cmd) {
        Reservation reservation = prepareReservation(cmd);
        reservation.collectPayment(cmd);
    }

    private Reservation prepareReservation(CollectPaymentCommand cmd) {
        Reservation reservation = reservationRepository.get(cmd.getReservationNumber());
        if (reservation == null) {
            throw new InvalidCommandException("reservationNumber", String.format("Reservation >>>%s<<< does not exist", cmd.getReservationNumber()));
        }
        addPaymentFacade(reservation, cmd);
        return reservation;
    }

    private void addPaymentFacade(Reservation reservation, CollectPaymentCommand cmd) {
        if (cmd.getType().equals(PaymentType.CREDIT_CARD)) {

        }
    }
}
