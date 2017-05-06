package pl.com.bottega.cinemac.model.reservation;


public interface ReservationRepository {

     void put(Reservation reservation);
     Reservation get(ReservationNumber reservationNumber);
}
