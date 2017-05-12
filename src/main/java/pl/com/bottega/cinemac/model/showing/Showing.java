package pl.com.bottega.cinemac.model.showing;

import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.pricing.Pricing;
import pl.com.bottega.cinemac.model.reservation.Reservation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Showing {

    @Id
    @GeneratedValue
    Long id;
    LocalDateTime beginsAt;

    LocalDateTime endsAt;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @ManyToOne
    private Movie movie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "showing")
    private Set<Reservation> reservations;

    Showing(){}

    public Showing(LocalDateTime date, Cinema cinema, Movie movie) {
        this.beginsAt = date;
        this.endsAt = beginsAt.plusMinutes(movie.getLength());
        this.cinema = cinema;
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return new CinemaHall(reservations);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBeginsAt() {
        return beginsAt;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Movie getMovie() {
        return movie;
    }


    public Pricing determinePricing() {
        return movie.getPricing();
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }
}