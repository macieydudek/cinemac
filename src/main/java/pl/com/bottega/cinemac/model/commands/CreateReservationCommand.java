package pl.com.bottega.cinemac.model.commands;

import pl.com.bottega.cinemac.model.*;

import java.util.Set;


public class CreateReservationCommand implements Validatable {

    private Long showId;
    private ReservationStatus status;
    private Customer customer;
    private Set<ReservationItem> tickets;
    private Set<Seat> seats;

    public ReservationStatus getStatus() {
        return status;
    }


    public Customer getCustomer() {
        return customer;
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTickets(Set<ReservationItem> tickets) {
        this.tickets = tickets;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public void validate(ValidationErrors errors) {
/*
        if(status == null)
            errors.add("status", "must be filled");

        validateReservationItems(errors);
        validateSeats(errors);*/
    }
    private void validateSeats(ValidationErrors errors) {

    }
    private void validateReservationItems(ValidationErrors errors) {

    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
