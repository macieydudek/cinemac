package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.MovieRepository;

public class JPAMovieRepository implements MovieRepository{
    @Override
    public void put(Movie movie) {

    }

    @Override
    public Movie get(Long id) {
        return null;
    }
}
