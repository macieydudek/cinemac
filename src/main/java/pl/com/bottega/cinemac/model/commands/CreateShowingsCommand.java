package pl.com.bottega.cinemac.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateShowingsCommand implements Validatable{

    @JsonProperty(required = true)
    private Long movieId;
    private List<String> dates;
    private Long cinemaId;
    private CustomCalendar calendar;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public CustomCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(CustomCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if(movieId == null)
            errors.add("movieId", "Can't be blank");
        if((dates == null || dates.isEmpty()) && calendar == null)
            errors.add("dates or calendar", "Fill dates or calendar, they can't be both blank");
    }

}

