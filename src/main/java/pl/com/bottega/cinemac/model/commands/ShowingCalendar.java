package pl.com.bottega.cinemac.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

public class ShowingCalendar {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime fromDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime untilDate;

    @JsonProperty(required = true)
    private List<String> weekDays;
    @JsonProperty(required = true)
    private List<String> hours;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate;
    }

    public List<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        this.weekDays = weekDays;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }
}
