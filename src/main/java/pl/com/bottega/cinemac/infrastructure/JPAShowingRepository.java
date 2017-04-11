package pl.com.bottega.cinemac.infrastructure;


import pl.com.bottega.cinemac.model.Showing;
import pl.com.bottega.cinemac.model.ShowingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAShowingRepository implements ShowingRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Showing showing) {
        entityManager.persist(showing);
    }
}
