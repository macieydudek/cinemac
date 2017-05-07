package pl.com.bottega.cinemac.application.implementation;

import com.itextpdf.text.DocumentException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.com.bottega.cinemac.application.PaymentListener;
import pl.com.bottega.cinemac.application.TicketMailer;
import pl.com.bottega.cinemac.model.events.ReservationPaidByCCEvent;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;

public class StandardPaymentListener implements PaymentListener{

    TicketMailer ticketMailer;
    ReservationRepository reservationRepository;

    public StandardPaymentListener(TicketMailer ticketMailer, ReservationRepository reservationRepository) {
        this.ticketMailer = ticketMailer;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @TransactionalEventListener
    @Async
    public void reservationPaidByCC(ReservationPaidByCCEvent event) throws DocumentException {
        ticketMailer.sendTickets(reservationRepository.get(event.getReservationNumber()));
    }

}
