package pl.com.bottega.cinemac.model.reservation;


import java.util.List;

public interface ReservationRepository {

     void put(Reservation reservation);
     Reservation get(ReservationNumber reservationNumber);

}
