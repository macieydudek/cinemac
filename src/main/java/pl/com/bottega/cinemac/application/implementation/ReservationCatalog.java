package pl.com.bottega.cinemac.application.implementation;


import pl.com.bottega.cinemac.application.ReservationDto;
import pl.com.bottega.cinemac.application.ReservationsQuery;
import pl.com.bottega.cinemac.model.reservation.Reservation;

import java.util.List;

public interface ReservationCatalog {


    public List<ReservationDto> getReservations(ReservationsQuery reservationQuery);
}
