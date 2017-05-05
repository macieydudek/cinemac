package pl.com.bottega.cinemac.ui;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

@RestController
public class ReservationController {

    ReservationProcess reservationProcess;

    public ReservationController(ReservationProcess reservationProcess) {
        this.reservationProcess = reservationProcess;
    }


    @PostMapping("/price_calculator")
    public CalculationResult calculatePrice(@RequestBody CalculatePriceCommand cmd) {
        return reservationProcess.calculatePrice(cmd);
    }
}
