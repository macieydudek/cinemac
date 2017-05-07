package pl.com.bottega.cinemac.application;

import com.itextpdf.text.DocumentException;
import pl.com.bottega.cinemac.model.events.ReservationPaidByCCEvent;

public interface PaymentListener {

    void reservationPaidByCC(ReservationPaidByCCEvent event) throws DocumentException;

}
