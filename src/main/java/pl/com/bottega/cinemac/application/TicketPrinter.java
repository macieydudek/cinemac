package pl.com.bottega.cinemac.application;


import com.itextpdf.text.DocumentException;

public interface TicketPrinter {
    byte[] printTickets(String reservationNumber) throws DocumentException;
}
