package pl.com.bottega.cinemac.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.CinemaHallDto;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;
import pl.com.bottega.cinemac.model.pricing.CalculationResult;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.pricing.Pricing;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationItem;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;
import pl.com.bottega.cinemac.model.showing.CinemaHall;
import pl.com.bottega.cinemac.model.showing.Showing;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StandardReservationProcess implements ReservationProcess {


    PriceCalculator priceCalculator;
    ShowingRepository showingRepository;
    ReservationRepository reservationRepository;
    EntityManager entityManager;



    public StandardReservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository, ReservationRepository reservationRepository) {
        this.priceCalculator = priceCalculator;
        this.showingRepository = showingRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public CinemaHallDto getSeats(Long showId) {
        CinemaHall cinemaHall = showingRepository.get(showId).getCinemaHall();
        return createCinemaHallDto(cinemaHall);
    }

    private CinemaHallDto createCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallDto dto = new CinemaHallDto();
        dto.setFree(cinemaHall.getFreeSeats());
        dto.setOccupied(cinemaHall.getOccupiedSeats());
        return dto;
    }

    @Override
    public CalculationResult calculatePrice(CalculatePriceCommand cmd) {
        Showing showing = showingRepository.get(cmd.getShowId());
        if (showing == null) {
            throw new InvalidCommandException("showingId", String.format("showing with ID:%s does not exist", cmd.getShowId()));
        }
        checkPricingCohesion(cmd, showing);
        return priceCalculator.calculatePrice(cmd);
    }

    @Override
    @Transactional
    public ReservationNumber create(CreateReservationCommand cmd) {
        Showing showing = showingRepository.get(cmd.getShowId());
        Reservation reservation = new Reservation(cmd, showing);
        CinemaHall cinemaHall = showing.getCinemaHall();
        if (!cinemaHall.isPossible(cmd))
            throw new InvalidCommandException("seats",
                    "You have to choose seats which are not taken and make sure you don't leave single seat surrounded by taken seats, unless there is no other option");
        reservationRepository.put(reservation);
        return reservation.getReservationNumber();

    }

    private void checkPricingCohesion(CalculatePriceCommand cmd, Showing showing) {
        Pricing pricing = showing.determinePricing();
        for (ReservationItem reservationItem : cmd.getTickets()) {
            if (!pricing.getPricing().containsKey(reservationItem.getKind())) {
                throw new InvalidCommandException("Tickets", String.format("Ticket kind >>%s<< not available for showing with ID:%d",
                        reservationItem.getKind(), showing.getId()));
            }
        }
    }


}