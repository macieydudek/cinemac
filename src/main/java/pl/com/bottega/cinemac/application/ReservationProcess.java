package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

public interface ReservationProcess {

    CinemaHallDto getSeats(Long showId);

    CalculationResult calculatePrice(CalculatePriceCommand cmd);
}
