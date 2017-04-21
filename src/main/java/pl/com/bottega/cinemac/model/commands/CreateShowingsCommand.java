package pl.com.bottega.cinemac.model.commands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreateShowingsCommand implements Validatable {

    private static final DateTimeFormatter CORRECT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm");
    private static final DateTimeFormatter CORRECT_TIME_FORMAT = DateTimeFormatter.ofPattern("kk:mm");

    private Long movieId;
    private List<String> dates;
    private Long cinemaId;
    private ShowingCalendar calendar;

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

    public ShowingCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowingCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validateMovieIdIsNotNull(errors);
        validateDatesOrCalendarAreFilled(errors);
        if (dates != null)
            validateDatesFormat(errors);
        if (calendar != null) {
            validateWeekDaysFormat(errors);
            validateHoursFormat(errors);
            validateFromDateFormat(errors);
            validateUntilDateFormat(errors);
        }
    }

    private void validateDatesOrCalendarAreFilled(ValidationErrors errors) {
        if ((dates == null || dates.isEmpty()) && calendar == null)
            errors.add("dates or calendar", "Fill dates or calendar, they can't be both blank");
    }

    private void validateMovieIdIsNotNull(ValidationErrors errors) {
        if (movieId == null)
            errors.add("movieId", "Can't be blank");
    }

    private void validateFromDateFormat(ValidationErrors errors) {
        validateSingleDateTimeFormat("fromDate", errors, calendar.getFromDate());
    }

    private void validateUntilDateFormat(ValidationErrors errors) {
        validateSingleDateTimeFormat("untilDate", errors, calendar.getUntilDate());
    }

    private void validateHoursFormat(ValidationErrors errors) {
        List<String> hours = calendar.getHours();

        if (hours == null || hours.isEmpty())
            errors.add("hours", "Can't be blank");

        for (String hour : hours) {
            try {
                LocalTime.parse(hour, CORRECT_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                errors.add("hours", "Incorrect hour format, correct format is HH:mm");
            }
        }
    }

    private void validateWeekDaysFormat(ValidationErrors errors) {
        try {
            List<String> weekDays = calendar.getWeekDays();

            if (weekDays == null || weekDays.isEmpty())
                errors.add("weekDays", "Can't be blank");

            for (String weekDay : weekDays)
                DayOfWeek.valueOf(weekDay.toUpperCase());

        } catch (IllegalArgumentException e) {
            errors.add("weekDays", "Incorrect weekDay name");
    }

}

    private void validateDatesFormat(ValidationErrors errors) {
        for (String date : dates) {
            validateSingleDateTimeFormat("dates", errors, date);
        }
    }

    private void validateSingleDateTimeFormat(String fieldName, ValidationErrors errors, String date) {
        try {
            LocalDateTime.parse(date, CORRECT_DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            errors.add(fieldName, "Incorrect date format, correct format is yyyy/MM/dd HH:mm");
        }
    }


}

