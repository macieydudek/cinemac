package pl.com.bottega.cinemac.model.commands;

import pl.com.bottega.cinemac.model.ReservationItem;

import java.util.Set;


public class CalculatePriceCommand {

    private Long showId;

    private Set<ReservationItem> tickets;

    public Long getShowId() {
        return showId;
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public void setTickets(Set<ReservationItem> tickets) {
        this.tickets = tickets;
    }
}
