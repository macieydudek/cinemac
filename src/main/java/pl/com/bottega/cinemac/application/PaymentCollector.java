package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.commands.CollectPaymentCommand;
import pl.com.bottega.cinemac.model.reservation.PaymentAttempt;

public interface PaymentCollector {

    PaymentAttempt collectPayment(CollectPaymentCommand cmd);

}
