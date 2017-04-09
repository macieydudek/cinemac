package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

import java.util.Set;

public class Movie {

    Long id;

    String title;

    String description;

    Set<String> actors;

    Set<String> genres;

    Integer minAge;

    Integer length;

    public Movie(CreateMovieCommand cmd) {

    }
}
