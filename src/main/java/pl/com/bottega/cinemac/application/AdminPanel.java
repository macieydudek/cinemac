package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;
import pl.com.bottega.cinemac.model.commands.DefineMoviePricingCommand;

import java.math.BigDecimal;
import java.util.Map;

public interface AdminPanel {

    void createCinema(CreateCinemaCommand cmd);

    void createMovie(CreateMovieCommand cmd);

    void createShowings(CreateShowingsCommand cmd);

    void defineMoviePrices(DefineMoviePricingCommand cmd);
}
