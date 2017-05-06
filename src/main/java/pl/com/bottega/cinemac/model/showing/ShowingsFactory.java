package pl.com.bottega.cinemac.model.showing;

import pl.com.bottega.cinemac.model.Cinema;
import pl.com.bottega.cinemac.model.Movie;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;
import pl.com.bottega.cinemac.model.commands.ShowingCalendar;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

public class ShowingsFactory {

    public List<Showing> createShowings(Cinema cinema, Movie movie, CreateShowingsCommand cmd) {
        List<Showing> showings = new LinkedList<>();
        if (cmd.getDates() != null)
            addShowingsOfDates(cinema, movie, cmd, showings);
        if (cmd.getCalendar() != null)
            addShowingsOfCalendar(cinema, movie, cmd, showings);
        return showings;
    }

    private void addShowingsOfCalendar(Cinema cinema, Movie movie, CreateShowingsCommand cmd, List<Showing> showings) {
        ShowingCalendar calendar = cmd.getCalendar();
        List<String> weekDays = calendar.getWeekDays();
        for (String day : weekDays) {
            LocalDateTime showingDate = getFirstWeekDayOfPeriod(calendar, day);
            for (String hour : calendar.getHours()) {
                LocalTime showTime = LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm"));
                setAndAddShowings(cinema, movie, showings, calendar, showingDate, showTime);
            }
        }
    }

    private void setAndAddShowings(Cinema cinema, Movie movie, List<Showing> showings, ShowingCalendar calendar, LocalDateTime showingDate, LocalTime showTime) {
        LocalDateTime showingDateAndTime = showingDate
                .withHour(showTime.getHour())
                .withMinute(showTime.getMinute());
        while (showingIsBeforeUntilDate(calendar, showingDateAndTime)) {
            showings.add(new Showing(showingDateAndTime, cinema, movie));
            showingDateAndTime = showingDateAndTime.plusWeeks(1L);
        }
    }

    private boolean showingIsBeforeUntilDate(ShowingCalendar calendar, LocalDateTime showingDate) {
        LocalDateTime untilDate = LocalDateTime.parse(calendar.getUntilDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        return (showingDate != null) && showingDate.isBefore(untilDate) || showingDate.isEqual(untilDate);
    }

    private LocalDateTime getFirstWeekDayOfPeriod(ShowingCalendar calendar, String day) {
        LocalDateTime fromDate = LocalDateTime.parse(calendar.getFromDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        return fromDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day.toUpperCase())));
    }

    private void addShowingsOfDates(Cinema cinema, Movie movie, CreateShowingsCommand cmd, List<Showing> showings) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm");
        for (String date : cmd.getDates()) {
            LocalDateTime resultDate = LocalDateTime.parse(date, dtf);
            showings.add(new Showing(resultDate, cinema, movie));
        }
    }
}
