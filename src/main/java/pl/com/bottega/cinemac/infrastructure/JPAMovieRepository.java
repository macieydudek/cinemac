package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAMovieRepository implements MovieRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Movie movie) {

    }

    @Override
    public Movie get(Long id) {
        return entityManager.find(Movie.class, id);
    }
}
