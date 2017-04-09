package pl.com.bottega.application;

import pl.com.bottega.model.commands.CreateCinemaCommand;
import pl.com.bottega.model.commands.CreateMovieCommand;
import pl.com.bottega.model.commands.CreateShowingCommand;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowings(CreateShowingCommand cmd);
}
