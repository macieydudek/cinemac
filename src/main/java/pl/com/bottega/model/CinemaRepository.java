package pl.com.bottega.model;

public interface CinemaRepository {

    void put(Cinema cinema);

    Cinema get(Long id);
}
