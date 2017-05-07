package pl.com.bottega.cinemac.infrastructure;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import pl.com.bottega.cinemac.application.TicketMailer;
import pl.com.bottega.cinemac.application.TicketPrinter;
import pl.com.bottega.cinemac.model.reservation.Reservation;

import javax.mail.internet.InternetAddress;

public class SMTPGmailTicketMailer implements TicketMailer {

    JavaMailSender javaMailSender;
    TicketPrinter ticketPrinter;

    public SMTPGmailTicketMailer(TicketPrinter ticketPrinter, JavaMailSender javaMailSender) {
        this.ticketPrinter = ticketPrinter;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendTickets(Reservation reservation) throws DocumentException {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(new InternetAddress(reservation.getCustomer().getEmail()));
            helper.setFrom(new InternetAddress("cinemacspzoo@gmail.com"));
            helper.setSubject(String.format("Reservation number: %s", reservation.getReservationNumber().getReservationNumber()));
            helper.setText(
                    "Dear " + reservation.getCustomer().getFirstName() + " "
                            + reservation.getCustomer().getLastName()
                            + ", thank you for your reservation. Your reservation number is "
                            + reservation.getReservationNumber().getReservationNumber());
            helper.addAttachment("tickets.pdf", new ByteArrayResource(ticketPrinter.printTickets(reservation.getReservationNumber().getReservationNumber())));
        };
        javaMailSender.send(preparator);

    }

}
