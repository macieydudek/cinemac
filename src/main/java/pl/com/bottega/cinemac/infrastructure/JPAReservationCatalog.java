package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.application.ReservationDto;
import pl.com.bottega.cinemac.application.ReservationsQuery;
import pl.com.bottega.cinemac.application.implementation.ReservationCatalog;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationStatus;
import pl.com.bottega.cinemac.model.showing.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class JPAReservationCatalog implements ReservationCatalog {

    @PersistenceContext
    EntityManager entityManager;
    private PriceCalculator priceCalculator;

    JPAReservationCatalog(PriceCalculator priceCalculator) {

        this.priceCalculator = priceCalculator;
    }

    @Override
    public List<ReservationDto> getReservations(ReservationsQuery reservationQuery) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = cb.createQuery(Reservation.class);
        Root<Reservation> reservationRoot = criteriaQuery.from(Reservation.class);
        criteriaQuery.where(cb.and(
                cb.like(reservationRoot.get("customer").get("lastName"), reservationQuery.getQuery()),
                cb.equal(reservationRoot.get("status"), ReservationStatus.valueOf(reservationQuery.getStatus().toUpperCase())
                )));
        criteriaQuery.having(cb.greaterThan(reservationRoot.get("showing").get("endsAt"), cb.currentTime()));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Reservation> reservations = query.getResultList();
        return createReservationDtos(reservations);
    }


    private List<ReservationDto> createReservationDtos(List<Reservation> reservations) {
        List<ReservationDto> dtos = new LinkedList<>();
        for (Reservation reservation : reservations) {
            ReservationDto dto = new ReservationDto();
            Showing showing = reservation.getShowing();
            Movie movie = showing.getMovie();
            dto.setNumber(reservation.getReservationNumber().getReservationNumber());
            dto.setMovieDto(movie.getId(), movie.getTitle());
            dto.setShow(showing.getId(), showing.getBeginsAt());
            dto.setTickets(reservation.getReservationItems());
            dto.setSeats(reservation.getSeats());
            dto.setCustomer(reservation.getCustomer());
            dto.setStatus(reservation.getStatus().name());
            CalculatePriceCommand cmd = new CalculatePriceCommand();
            cmd.setShowId(showing.getId());
            cmd.setTickets(reservation.getReservationItems());
            dto.setTotalPrice(priceCalculator.calculatePrice(cmd).getTotalPrice());
            dtos.add(dto);
        }
        return dtos;
    }

}
