package pl.com.bottega.cinemac.ui;


import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.application.TicketPrinter;
import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.ReservationNumber;
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

    @PostMapping("/reservations/{reservationNumber}/payments")
    public void collectPayment(@PathVariable ReservationNumber reservationNumber, @RequestBody CollectPaymentCommand cmd) {
        cmd.setReservationNumber(reservationNumber);
        paymentCollector.collectPayment(cmd);
    }

    @GetMapping("/reservations/{reservationNumber}/tickets")
    public void printTickets(HttpServletResponse response, @PathVariable String reservationNumber) {
        response.setContentType("application/pdf");
        try {
            OutputStream os = response.getOutputStream();
            os.write(ticketPrinter.printTickets(reservationNumber));
            os.flush();
            os.close();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
