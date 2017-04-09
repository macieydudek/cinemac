package pl.com.bottega.infrastructure;

import pl.com.bottega.model.Movie;
import pl.com.bottega.model.MovieRepository;

public class JPAMovieRepository implements MovieRepository{
    @Override
    public void put(Movie movie) {

    }

    @Override
    public Movie get(Long id) {
        return null;
    }
}
