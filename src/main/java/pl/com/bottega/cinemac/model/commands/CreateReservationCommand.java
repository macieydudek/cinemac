package pl.com.bottega.cinemac.model.commands;

import pl.com.bottega.cinemac.model.reservation.Customer;
import pl.com.bottega.cinemac.model.reservation.ReservationItem;
import pl.com.bottega.cinemac.model.reservation.ReservationStatus;
import pl.com.bottega.cinemac.model.showing.Seat;

import java.util.HashSet;
import java.util.Set;

public class CreateReservationCommand implements Validatable {


    private static final int MAX_ROW = 10;
    private static final int MIN_ROW = 1;
    private static final int MAX_SEAT = 15;
    private static final int MIN_SEAT = 1;
    private Customer customer;
    private Set<ReservationItem> tickets;

    private Set<Seat> seats;
    private Long showId;
    private ReservationStatus status;


    public Customer getCustomer() {
        return customer;
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
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
        validateTicketQuantity(errors);
        validateTicketKind(errors);
        validateSeats(errors);
        validateCustomer(errors);
    }

    private void validateTicketQuantity(ValidationErrors errors) {
        ensureTicketsNotEmpty(errors);
        for (ReservationItem ticket : tickets) {
            validateSingleTicketQuantity(ticket, errors);
        }
    }

    private void validateSingleTicketQuantity(ReservationItem ticket, ValidationErrors errors) {
        if (ticket.getCount() == null || ticket.getCount() <= 0) {
            errors.add("count", "Has to be positive");
        }
    }

    private void ensureTicketsNotEmpty(ValidationErrors errors) {
        if (tickets == null || tickets.isEmpty()) {
            errors.add("tickets", "cannot be empty");
        }
    }


    private void validateTicketKind(ValidationErrors errors) {
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

    //
    private void validateCustomer(ValidationErrors errors) {
        if (isEmpty(customer.firstName)) {
            errors.add("firstName", "Customer must have a name !");
        }
        if (isEmpty(customer.lastName)) {
            errors.add("lastName", "Customer must have a lastName !");
        }
        validateCustomerEmail(errors);

        if (customer.phone == null || isEmpty(customer.phone.toString())) {
            errors.add("phone", "Phone is required");
        }
    }

    private void validateCustomerEmail(ValidationErrors errors) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String email = customer.getEmail();
        if (isEmpty(email)) {
            errors.add("email", "Customer must have a email");
            return;
        }
        if (!email.matches(EMAIL_REGEX)) {
            errors.add("email", "Email address is invalid");
        }
    }


    private void validateSeats(ValidationErrors errors) {
        if (seats == null || seats.isEmpty()) {
            errors.add("seats", "This field can`t be blank");
        }
        validateSeatCount(errors);
        for (Seat seat : seats) {
            validateRow(errors, seat);
            validateSeat(errors, seat);
        }
    }

    private void validateSeatCount(ValidationErrors errors) {
        Long totalCount = countTickets();
        if (totalCount != seats.size()) {
            errors.add("seats", "seat count has to correspond to ticket count");
        }
    }

    private Long countTickets() {
        Long total = 0L;
        for (ReservationItem ticket : tickets) {
            total += ticket.getCount();
        }
        return total;
    }

    private void validateSeat(ValidationErrors errors, Seat seat) {
        if (seat.getSeat() == null)
            errors.add("seat", "Field cant be blank");
        if (seat.getSeat() > MAX_SEAT && seat.getSeat() < MIN_SEAT)
            errors.add("seat", "Max seat is 15, Min seat is 1");
    }

    private void validateRow(ValidationErrors errors, Seat seat) {
        if (seat.getRow() == null)
            errors.add("row", "Field cant be blank");
        if (seat.getRow() > MAX_ROW && seat.getRow() < MIN_ROW)
            errors.add("row", "Max row is 10, Min row is 1");
    }


    public Long getShowId() {
        return showId;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
