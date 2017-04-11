package pl.com.bottega.cinemac.application.implementation;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.model.*;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;

import java.util.List;

@Transactional
public class StandardAdminPanel implements AdminPanel {

    private CinemaRepository cinemaRepository;
    private ShowingsFactory showingsFactory;
    private MovieRepository movieRepository;
    private ShowingRepository showingRepository;

    public StandardAdminPanel(CinemaRepository cinemaRepository, ShowingsFactory showingsFactory, MovieRepository movieRepository, ShowingRepository showingRepository) {
        this.cinemaRepository = cinemaRepository;
        this.showingsFactory = showingsFactory;
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository;
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
    public void createShowings(CreateShowingsCommand cmd) {
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        Movie movie = movieRepository.get(cmd.getMovieId());
        List<Showing> showings = showingsFactory.createShowings(cinema, movie, cmd);
        for (Showing showing : showings)
            showingRepository.put(showing);
    }
}
