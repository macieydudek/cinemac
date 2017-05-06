package pl.com.bottega.cinemac.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Showing {

    @Id
    @GeneratedValue
    Long id;
    LocalDateTime beginsAt;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @ManyToOne
    private Movie movie;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    Showing(){}

    public Showing(LocalDateTime date, Cinema cinema, Movie movie) {
        this.beginsAt = date;
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
}