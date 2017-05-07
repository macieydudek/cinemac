package pl.com.bottega.cinemac.application;

import com.itextpdf.text.DocumentException;
import pl.com.bottega.cinemac.model.reservation.Reservation;

public interface TicketMailer {

    void sendTickets(Reservation reservation) throws DocumentException;

}
