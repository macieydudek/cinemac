package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

/**
 * Created by Adam.Wronski on 2017-04-23.
 */
public interface ReservationProcess {

    CinemaHallDto getSeats(Long showId);
    CalculationResult calculatePrice(CalculatePriceCommand cmd);
    ReservationNumber create(CreateReservationCommand cmd);
}
