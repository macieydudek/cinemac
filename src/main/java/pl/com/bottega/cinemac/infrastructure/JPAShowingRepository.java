package pl.com.bottega.cinemac.infrastructure;


import pl.com.bottega.cinemac.model.Showing;
import pl.com.bottega.cinemac.model.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAShowingRepository implements ShowingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Showing showing) {
        entityManager.persist(showing);
    }

    @Override
    public Showing get(Long id) {
        return entityManager.find(Showing.class, id);
    }

    @Override
    public boolean isAlreadyAdded(Showing showing) {
        Query query = entityManager.createQuery("SELECT s FROM Showing s WHERE s.cinema = :cinema AND s.movie = :movie AND s.beginsAt = :beginsAt");
        query.setParameter("cinema", showing.getCinema());
        query.setParameter("movie", showing.getMovie());
        query.setParameter("beginsAt", showing.getBeginsAt());
        return !query.getResultList().isEmpty();
    }
}
