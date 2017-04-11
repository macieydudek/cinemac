package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    Long id;

    String title;

    String description;

    /*
    Wykomentowałem, żeby się nie wtrącać w nieswoje kejsy,
    a musiałem z tego zrobić encję - Kuba
    Set<String> actors;

    Set<String> genres;*/

    Integer minAge;

    Integer length;

    Movie() {
    }

    public Movie(CreateMovieCommand cmd) {

    }
}
