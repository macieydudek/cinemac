package pl.com.bottega.cinemac.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    Showing(){}

    public Showing(LocalDateTime date, Cinema cinema, Movie movie) {
        this.beginsAt = date;
        this.cinema = cinema;
        this.movie = movie;
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
}
