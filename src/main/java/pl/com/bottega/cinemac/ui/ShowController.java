package pl.com.bottega.cinemac.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemac.application.CinemaHallDto;
import pl.com.bottega.cinemac.application.ReservationProcess;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private ReservationProcess reservationProcess;

    public ShowController(ReservationProcess reservationProcess) {
        this.reservationProcess = reservationProcess;
    }

    @GetMapping("/{showId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showId) {
        return reservationProcess.getSeats(showId);
    }
}
