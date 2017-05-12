package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.application.implementation.ReservationCatalog;
import pl.com.bottega.cinemac.model.pricing.CalculationResult;
import pl.com.bottega.cinemac.model.reservation.Reservation;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

import java.util.List;

public interface ReservationProcess {

    CinemaHallDto getSeats(Long showId);

    CalculationResult calculatePrice(CalculatePriceCommand cmd);

    ReservationNumber create(CreateReservationCommand cmd);
}
