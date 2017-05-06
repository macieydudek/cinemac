package pl.com.bottega.cinemac.model.commands;

import pl.com.bottega.cinemac.model.reservation.ReservationItem;

import java.util.HashSet;
import java.util.Set;

public class CalculatePriceCommand implements Validatable {

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

    @Override
    public void validate(ValidationErrors errors) {
        validateShowId(errors);
        validateTickets(errors);
    }

    private void validateTickets(ValidationErrors errors) {
        if (tickets == null || tickets.isEmpty()) {
            errors.add("tickets", "Has to be defined.");
            return;
        }
        ensureUniqueTicketType(errors);
        checkTicketCount(errors);
    }

    private void checkTicketCount(ValidationErrors errors) {
        for (ReservationItem reservationItem : tickets) {
            Long count = reservationItem.getCount();
            if (count == null) {
                errors.add("count", String.format("Count for ticket kind >>%s<< has to be defined", reservationItem.getKind()));
                return;
            }
            if (count <= 0) {
                errors.add("count", String.format("Count for ticket kind >>%s<< has to be positive", reservationItem.getKind()));
            }
        }
    }

    private void ensureUniqueTicketType(ValidationErrors errors) {
            Set<String> tmp = new HashSet<>();
            for (ReservationItem reservationItem : tickets) {
                String ticketKind = reservationItem.getKind();
                if (isEmpty(ticketKind)) {
                    errors.add("ticket type", "All ticket types have to be defined.");
                }
                if (tmp.contains(ticketKind)) {
                    errors.add("tickets", String.format("Ticket kind >>%s<< cannot be duplicated", ticketKind));
                }
                tmp.add(reservationItem.getKind());
            }
    }

    private void validateShowId(ValidationErrors errors) {
        if (showId == null) {
            errors.add("showId", "Has to be defined.");
        }
    }
}
