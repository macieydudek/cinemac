package pl.com.bottega.cinemac.application;

import java.util.List;

public class MovieShowingsDto {

    private MovieDto movie;
    private List<Show> shows;

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
