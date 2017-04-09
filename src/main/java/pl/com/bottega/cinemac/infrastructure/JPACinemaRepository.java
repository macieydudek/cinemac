package pl.com.bottega.cinemac.infrastructure;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.CinemaRepository;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.commands.InvalidCommandException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPACinemaRepository implements CinemaRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void put(Cinema cinema) {
        if (isAlreadyAdded(cinema)) {
           throw new InvalidUserActionException(String.format("Cinema %s in %s is already added", cinema.getName(), cinema.getCity()));
        }
        entityManager.persist(cinema);
    }

    private boolean isAlreadyAdded(Cinema cinema) {
        Query query = entityManager.createQuery("SELECT c FROM Cinema c WHERE c.city = :city AND c.name = :name");
        query.setParameter("city", cinema.getCity());
        query.setParameter("name", cinema.getName());
        return !query.getResultList().isEmpty();
    }

    @Override
    public Cinema get(Long id) {
        return null;
    }
}
