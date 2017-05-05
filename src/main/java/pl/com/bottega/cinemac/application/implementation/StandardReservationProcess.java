package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.CinemaHallDto;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.*;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

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
        return priceCalculator.calculatePrice(cmd);
    }
}
