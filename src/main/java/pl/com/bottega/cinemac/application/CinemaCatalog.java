package pl.com.bottega.cinemac.application;

import java.time.LocalDate;
import java.util.List;

public interface CinemaCatalog {

    List<CinemaDto> getCinemas();

    List<MovieShowingsDto> getShowings(Long cinemaId, LocalDate date);
}
