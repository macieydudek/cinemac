package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.application.ReservationDto;
import pl.com.bottega.cinemac.application.ReservationsQuery;
import pl.com.bottega.cinemac.application.TicketDto;
import pl.com.bottega.cinemac.application.implementation.ReservationCatalog;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationItem;
import pl.com.bottega.cinemac.model.reservation.ReservationStatus;
import pl.com.bottega.cinemac.model.showing.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
                cb.equal(reservationRoot.get("status"), ReservationStatus.valueOf(reservationQuery.getStatus().toUpperCase())),
                cb.greaterThan(reservationRoot.get("showing").get("endsAt"), cb.currentTimestamp())
                ));
        criteriaQuery.orderBy(cb.asc(reservationRoot.get("showing").get("beginsAt")));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Reservation> reservations = query.getResultList();
        return createReservationDtos(reservations);
    }


    private List<ReservationDto> createReservationDtos(List<Reservation> reservations) {
        List<ReservationDto> dtos = new LinkedList<>();
        for (Reservation reservation : reservations) {
            dtos.add(getReservationDto(reservation));
        }
        return dtos;
    }

    private ReservationDto getReservationDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        Showing showing = reservation.getShowing();
        Movie movie = showing.getMovie();
        dto.setNumber(reservation.getReservationNumber().getReservationNumber());
        dto.setMovieDto(movie.getId(), movie.getTitle());
        dto.setShow(showing.getId(), showing.getBeginsAt());
        dto.setTickets(createTicketDtos(reservation, movie));
        dto.setSeats(reservation.getSeats());
        dto.setCustomer(reservation.getCustomer());
        dto.setStatus(reservation.getStatus().name());
        CalculatePriceCommand cmd = new CalculatePriceCommand();
        cmd.setShowId(showing.getId());
        cmd.setTickets(reservation.getReservationItems());
        dto.setTotalPrice(priceCalculator.calculatePrice(cmd).getTotalPrice());
        return dto;
    }

    private Set<TicketDto> createTicketDtos(Reservation reservation, Movie movie) {
        Set<TicketDto> dtos = new HashSet<>();
        for (ReservationItem item : reservation.getReservationItems()) {
            dtos.add(getTicketDto(movie, item));
        }
        return dtos;
    }

    private TicketDto getTicketDto(Movie movie, ReservationItem item) {
        TicketDto dto = new TicketDto();
        dto.setCount(item.getCount());
        dto.setKind(item.getKind());
        dto.setUnitPrice(movie.getPricing().getPricing().get(item.getKind()));
        dto.setTotalPrice(dto.getUnitPrice().multiply(BigDecimal.valueOf(item.getCount())));
        return dto;
    }

}
