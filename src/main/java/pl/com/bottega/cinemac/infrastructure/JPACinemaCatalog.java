package pl.com.bottega.cinemac.infrastructure;

import pl.com.bottega.cinemac.application.*;
import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.showing.Showing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CinemaDto> getCinemas() {
        Query query = entityManager.createQuery("SELECT c FROM Cinema c");
        List<Cinema> cinemas = (List<Cinema>) query.getResultList();
        List<CinemaDto> results = createCinemaDtos(cinemas);
        return results;
    }

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> movies = entityManager.createQuery("SELECT m FROM Movie m").getResultList();
        return createMovieDtos(movies);
    }

    private List<MovieDto> createMovieDtos(List<Movie> movies) {
        List<MovieDto> dtos = new LinkedList<>();
        for(Movie movie : movies)
            dtos.add(createMovieDto(movie));
        return dtos;
    }

    @Override
    public List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date) {
        List<MovieShowingsDto> dtos = new LinkedList<>();
        Query movieQuery = entityManager.createQuery("SELECT DISTINCT m FROM Movie m JOIN FETCH m.showings s WHERE s.cinema.id = :id AND s.beginsAt BETWEEN :startDate AND :endDate ORDER BY m.title ASC ");
        movieQuery.setParameter("id", cinemaId);
        movieQuery.setParameter("startDate", date.atStartOfDay());
        movieQuery.setParameter("endDate", date.plusDays(1).atStartOfDay());
        List<Movie> movies = movieQuery.getResultList();
        for (Movie movie : movies) {
            dtos.add(createMovieShowingsDto(movie));
        }
        return dtos;
    }

    private MovieShowingsDto createMovieShowingsDto(Movie movie) {
        MovieShowingsDto movieShowingsDto = new MovieShowingsDto();
        movieShowingsDto.setShows(createMovieShows(movie.getShowings()));
        movieShowingsDto.setMovie(createMovieDto(movie));
        return movieShowingsDto;
    }

    private MovieDto createMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setMinAge(movie.getMinAge());
        movieDto.setLength(movie.getLength());
        movieDto.setActors(movie.getActors());
        movieDto.setGenres(movie.getGenres());
        return movieDto;
    }

    private List<Show> createMovieShows(Set<Showing> showings) {
        List<Show> shows = new LinkedList<>();
        for(Showing showing : showings) {
            shows.add(new Show(showing.getId(), showing.getBeginsAt().toLocalTime()));
        }
        Collections.sort(shows);
        return shows;
    }

    private List<CinemaDto> createCinemaDtos(List<Cinema> cinemas) {
        List<CinemaDto> cinemaDtos = new LinkedList<>();
        for (Cinema c : cinemas) {
            cinemaDtos.add(createDto(c));
        }
        return cinemaDtos;
    }

    private CinemaDto createDto(Cinema cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinema.getId());
        cinemaDto.setCity(cinema.getCity());
        cinemaDto.setName(cinema.getName());
        return cinemaDto;
    }
}
