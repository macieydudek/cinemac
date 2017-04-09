package pl.com.bottega.cinemac.model;

public interface CinemaRepository {

    void put(Cinema cinema);

    Cinema get(Long id);
}
