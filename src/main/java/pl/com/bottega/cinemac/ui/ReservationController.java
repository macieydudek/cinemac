package pl.com.bottega.cinemac.ui;


import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.application.TicketPrinter;
import pl.com.bottega.cinemac.model.pricing.CalculationResult;
import pl.com.bottega.cinemac.model.reservation.PaymentAttempt;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.commands.CreateReservationCommand;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class ReservationController {

    ReservationProcess reservationProcess;
    PaymentCollector paymentCollector;
    TicketPrinter ticketPrinter;

    public ReservationController(ReservationProcess reservationProcess, PaymentCollector paymentCollector, TicketPrinter ticketPrinter) {
        this.reservationProcess = reservationProcess;
        this.paymentCollector = paymentCollector;
        this.ticketPrinter = ticketPrinter;
    }

    @PutMapping("/reservations")
    public ReservationNumber createReservation(@RequestBody CreateReservationCommand cmd) {
        return reservationProcess.create(cmd);
    }

    @PostMapping("/price_calculator")
    public CalculationResult calculatePrice(@RequestBody CalculatePriceCommand cmd) {
        return reservationProcess.calculatePrice(cmd);
    }

    @PutMapping("/reservations/{reservationNumber}/payments")
    public PaymentAttempt collectPayment(@PathVariable ReservationNumber reservationNumber, @RequestBody CollectPaymentCommand cmd) {
        cmd.setReservationNumber(reservationNumber);
        return paymentCollector.collectPayment(cmd);
    }

    @GetMapping("/reservations/{reservationNumber}/tickets")
    public void printTickets(HttpServletResponse response, @PathVariable String reservationNumber) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        OutputStream os = response.getOutputStream();
        os.write(ticketPrinter.printTickets(reservationNumber));
        os.flush();
        os.close();
    }

}
