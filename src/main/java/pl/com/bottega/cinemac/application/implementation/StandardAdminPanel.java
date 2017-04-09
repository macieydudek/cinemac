package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.CinemaRepository;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
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
        if (cinemaRepository.isAlreadyAdded(cinema)) {
            throw new InvalidUserActionException(String.format("Cinema %s in %s is already added", cinema.getName(), cinema.getCity()));
        }
        cinemaRepository.put(cinema);
    }

    @Override
    public void createMovie(CreateMovieCommand cmd) {

    }

    @Override
    public void createShowings(CreateShowingCommand cmd) {

    }
}
