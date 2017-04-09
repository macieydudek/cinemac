package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.CinemaRepository;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingCommand;

public class StandardAdminPanel implements AdminPanel {

    private CinemaRepository cinemaRepository;

    public StandardAdminPanel(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void createCinema(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {

    }

    @Override
    public void createShowings(CreateShowingCommand cmd) {

    }
}
