package pl.com.bottega.cinemac.ui;


import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.Reservation;
import pl.com.bottega.cinemac.model.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

@RestController
public class ReservationController {

    ReservationProcess reservationProcess;
    PaymentCollector paymentCollector;

    public ReservationController(ReservationProcess reservationProcess, PaymentCollector paymentCollector) {
        this.reservationProcess = reservationProcess;
        this.paymentCollector = paymentCollector;

    }

    @PutMapping("/reservations")
    public ReservationNumber createReservation(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @PostMapping("/price_calculator")
    public CalculationResult calculatePrice(@RequestBody CalculatePriceCommand cmd) {
        return reservationProcess.calculatePrice(cmd);
    }

    @PostMapping("/reservations/{reservationNumber}/payments")
    public void collectPayment(@PathVariable ReservationNumber reservationNumber, @RequestBody CollectPaymentCommand cmd) {
        cmd.setReservationNumber(reservationNumber);
        paymentCollector.collectPayment(cmd);
    }

}
