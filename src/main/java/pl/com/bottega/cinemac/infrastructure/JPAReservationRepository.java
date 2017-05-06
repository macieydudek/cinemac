package pl.com.bottega.cinemac.infrastructure;


import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAReservationRepository implements ReservationRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Reservation get(ReservationNumber reservationNumber) {
        return entityManager.find(Reservation.class, reservationNumber);
    }
}
