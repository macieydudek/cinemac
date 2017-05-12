package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.reservation.Customer;
import pl.com.bottega.cinemac.model.reservation.ReservationItem;
import pl.com.bottega.cinemac.model.showing.Seat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDto {

    String number;
    ShowingDto show;
    MovieDto movieDto;
    Set<ReservationItem> tickets;
    Set<Seat> seats;
    Customer customer;
    String status;
    BigDecimal totalPrice;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ShowingDto getShow() {
        return show;
    }

    public void setShow(Long id, LocalDateTime time) {
        this.show = new ShowingDto(id, time);
    }

    public MovieDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(Long id, String title) {
        this.movieDto = new MovieDto(id, title);
    }

    public Set<ReservationItem> getTickets() {
        return tickets;
    }

    public void setTickets(Set<ReservationItem> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private class MovieDto {
        Long id;
        String title;

        public MovieDto(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    private class ShowingDto {
        Long id;
        LocalDateTime time;

        public ShowingDto(Long id, LocalDateTime time) {
            this.id = id;
            this.time = time;
        }

        public Long getId() {
            return id;
        }

        public LocalDateTime getTime() {
            return time;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
    }
}