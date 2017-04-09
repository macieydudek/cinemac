package pl.com.bottega.model;

import pl.com.bottega.model.commands.CreateMovieCommand;

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
