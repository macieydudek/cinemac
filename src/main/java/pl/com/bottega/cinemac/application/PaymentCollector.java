package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;

public interface PaymentCollector {

    void collectPayment(CollectPaymentCommand cmd);

}
