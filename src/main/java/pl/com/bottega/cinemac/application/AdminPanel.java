package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowings(CreateShowingCommand cmd);
}
