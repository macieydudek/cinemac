package pl.com.bottega.cinemac.model;


public interface ReservationRepository {

     void put(Reservation reservation);
     Reservation get(ReservationNumber reservationNumber);
}
