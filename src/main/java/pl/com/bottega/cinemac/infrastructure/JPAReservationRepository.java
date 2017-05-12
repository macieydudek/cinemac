package pl.com.bottega.cinemac.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;
import pl.com.bottega.cinemac.model.showing.Showing;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

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
