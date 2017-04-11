package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;
import pl.com.bottega.cinemac.model.commands.CustomCalendar;

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
        CustomCalendar calendar = cmd.getCalendar();
        List<String> weekDays = calendar.getWeekDays();
        for (String day : weekDays) {
            LocalDateTime showingDate = getFirstWeekDayOfPeriod(calendar, day);
            while (showingIsBeforeUntilDate(calendar, showingDate)) {
                for (String hour : calendar.getHours()) {
                    setAndAddShowing(cinema, movie, showings, showingDate, hour);
                }
                showingDate = showingDate.plusWeeks(1L);
            }
        }
    }

    private void setAndAddShowing(Cinema cinema, Movie movie, List<Showing> showings, LocalDateTime showingDate, String hour) {
        LocalTime showTime = LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm"));
        LocalDateTime showing = showingDate
                .withHour(showTime.getHour())
                .withMinute(showTime.getMinute());
        showings.add(new Showing(showing, cinema, movie));
    }

    private boolean showingIsBeforeUntilDate(CustomCalendar calendar, LocalDateTime showingDate) {
        return (showingDate != null) && showingDate.isBefore(calendar.getUntilDate());
    }

    private LocalDateTime getFirstWeekDayOfPeriod(CustomCalendar calendar, String day) {
        return calendar.getFromDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(day.toUpperCase())));
    }

    private void addShowingsOfDates(Cinema cinema, Movie movie, CreateShowingsCommand cmd, List<Showing> showings) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm");
        for (String date : cmd.getDates()) {
            LocalDateTime resultDate = LocalDateTime.parse(date, dtf);
            showings.add(new Showing(resultDate, cinema, movie));
        }
    }
}
