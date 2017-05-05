package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.CinemaHallDto;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.*;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;

public class StandardReservationProcess implements ReservationProcess {

    PriceCalculator priceCalculator;
    ShowingRepository showingRepository;

    public StandardReservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository) {
        this.priceCalculator = priceCalculator;
        this.showingRepository = showingRepository;
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
