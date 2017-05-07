package pl.com.bottega.cinemac.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAReservationRepository implements ReservationRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PaymentFacade paymentFacade;

    @Override
    public void put(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Reservation get(ReservationNumber reservationNumber) {
        Reservation reservation = entityManager.find(Reservation.class, reservationNumber);
        reservation.setPaymentFacade(paymentFacade);
        return reservation;
    }
}
