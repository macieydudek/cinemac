package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    Long id;

    String title;

    String description;

    @ElementCollection
    Set<String> actors;

    @ElementCollection
    Set<String> genres;

    Integer minAge;

    Integer length;

    Movie() {}

    public Movie(CreateMovieCommand cmd) {

    }
}
