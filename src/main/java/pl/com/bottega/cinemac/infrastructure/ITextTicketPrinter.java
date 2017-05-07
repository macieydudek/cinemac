package pl.com.bottega.cinemac.infrastructure;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.com.bottega.cinemac.application.TicketPrinter;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.reservation.*;
import pl.com.bottega.cinemac.model.showing.Showing;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

public class ITextTicketPrinter implements TicketPrinter {

    private ReservationRepository reservationRepository;
    private ShowingRepository showingRepository;

    public ITextTicketPrinter(ReservationRepository reservationRepository, ShowingRepository showingRepository) {
        this.reservationRepository = reservationRepository;
        this.showingRepository = showingRepository;
    }

    @Override
    public byte[] printTickets(String reservationNumber) throws DocumentException {
        Document document = new Document();
        Reservation reservation = reservationRepository.get(new ReservationNumber(reservationNumber));
        ensureCorrectNumber(reservation);
        ensureIsPaid(reservation);
        Showing showing = showingRepository.get(reservation.getShowId());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        writeTicketsToDocument(document, reservation, showing);
        return baos.toByteArray();
    }

    private void ensureIsPaid(Reservation reservation) {
        if(reservation.getStatus() != ReservationStatus.PAID)
            throw new InvalidUserActionException("Reservation have to be PAID to print tickets");
    }

    private void ensureCorrectNumber(Reservation reservation) {
        if(reservation == null)
            throw new InvalidUserActionException("Wrong reservationNumber");
    }

    private void writeTicketsToDocument(Document document, Reservation reservation, Showing showing) throws DocumentException {
        document.open();
        Iterator seatsIterator = reservation.getSeats().iterator();
        for(ReservationItem ri : reservation.getReservationItems()) {
            for(int i = 0; i < ri.getCount(); i++) {
                PdfPTable table = prepareTable(showing, seatsIterator, ri);
                document.add(table);
            }
        }
        document.close();
    }

    private PdfPTable prepareTable(Showing showing, Iterator seatsIterator, ReservationItem ri) {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(20);
        table.setSpacingBefore(20);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(getCell(showing.getMovie().getTitle()));
        table.addCell(getCell(seatsIterator.next().toString()));
        table.addCell(getCell(showing.getBeginsAt().toLocalTime().toString()));
        table.addCell(getCell(String.valueOf(showing.getMovie().getLength()) + " min"));
        return table;
    }

    public PdfPCell getCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.FontFamily.UNDEFINED, 15)));
        cell.setPadding(10);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }
}
