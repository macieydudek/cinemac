package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.PaymentCollector;
import pl.com.bottega.cinemac.model.ReservationNumber;
import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;

public class StandardPaymentCollector implements PaymentCollector{

    @Override
    public void collectPayment(CollectPaymentCommand cmd) {

    }
}
