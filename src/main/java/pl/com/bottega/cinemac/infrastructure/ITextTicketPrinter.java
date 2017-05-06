package pl.com.bottega.cinemac.infrastructure;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import pl.com.bottega.cinemac.application.TicketPrinter;

import java.io.ByteArrayOutputStream;

public class ITextTicketPrinter implements TicketPrinter {

    @Override
    public byte[] printTickets(String reservationNumber) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Bilet"));
        document.close();
        return baos.toByteArray();
    }
}
