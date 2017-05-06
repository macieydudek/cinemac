package pl.com.bottega.cinemac.model.showing;

public interface ShowingRepository {

    void put(Showing showing);

    Showing get(Long id);

    boolean isAlreadyAdded(Showing showing);
}
