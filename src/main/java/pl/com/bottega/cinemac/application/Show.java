package pl.com.bottega.cinemac.application;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class Show implements Comparable {
    private Long id;
    @JsonFormat(pattern = "kk:mm")
    private LocalTime time;

    public Show(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public int compareTo(Object o) {
        Show show = (Show) o;
        if (show.getTime().isBefore(time))
            return 1;
        else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        if (!id.equals(show.id)) return false;
        return time.equals(show.time);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
