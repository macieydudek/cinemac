package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.pricing.CalculationResult;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

public interface ReservationProcess {

    CinemaHallDto getSeats(Long showId);
    CalculationResult calculatePrice(CalculatePriceCommand cmd);
    ReservationNumber create(CreateReservationCommand cmd);
}
