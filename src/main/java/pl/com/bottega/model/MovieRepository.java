package pl.com.bottega.model;

public interface MovieRepository {

    void put(Movie movie);

    Movie get(Long id);
}
