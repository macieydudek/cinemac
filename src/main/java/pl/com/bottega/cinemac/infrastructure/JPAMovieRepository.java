package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAMovieRepository implements MovieRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie get(Long id) {
        return entityManager.find(Movie.class, id);
    }
}
