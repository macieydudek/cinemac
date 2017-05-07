package pl.com.bottega.cinemac.infrastructure;


import pl.com.bottega.cinemac.model.showing.Showing;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;

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
        Query query = entityManager.createQuery("SELECT s FROM Showing s LEFT JOIN FETCH s.reservations r LEFT JOIN FETCH r.seats WHERE s.id = :id");
        query.setParameter("id", id);
        return (Showing) query.getSingleResult();
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
