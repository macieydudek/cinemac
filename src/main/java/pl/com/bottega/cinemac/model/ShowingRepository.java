package pl.com.bottega.cinemac.model;

public interface ShowingRepository {

    void put(Showing showing);

    boolean isAlreadyAdded(Showing showing);
}
