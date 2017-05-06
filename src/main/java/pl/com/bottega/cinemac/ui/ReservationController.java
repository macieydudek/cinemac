package pl.com.bottega.cinemac.ui;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.Reservation;
import pl.com.bottega.cinemac.model.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

@RestController
public class ReservationController {

    ReservationProcess reservationProcess;

    public ReservationController(ReservationProcess reservationProcess) {
        this.reservationProcess = reservationProcess;
    }

    @PutMapping("/reservations")
    public ReservationNumber createReservation(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @PostMapping("/price_calculator")
    public CalculationResult calculatePrice(@RequestBody CalculatePriceCommand cmd) {
        return reservationProcess.calculatePrice(cmd);
    }
}
