package pl.com.bottega.cinemac.model;

public interface MovieRepository {

    void put(Movie movie);

    Movie get(Long id);
}
