package pl.com.bottega.infrastructure;

import pl.com.bottega.application.CinemaCatalog;
import pl.com.bottega.application.CinemaDto;
import pl.com.bottega.model.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedList;
import java.util.List;

public class JPACinemaCatalog implements CinemaCatalog{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        Query query = entityManager.createQuery("SELECT c FROM Cinema c");
        List<Cinema> cinemas = (List<Cinema>) query.getResultList();
        List<CinemaDto> results = createCinemaDtos(cinemas);
        return results;
    }

    private List<CinemaDto> createCinemaDtos(List<Cinema> cinemas) {
        List<CinemaDto> cinemaDtos = new LinkedList<>();
        for (Cinema c : cinemas) {
            cinemaDtos.add(createDto(c));
        }
        return cinemaDtos;
    }


    private CinemaDto createDto(Cinema cinema) {
        return new CinemaDto();
    }
}
