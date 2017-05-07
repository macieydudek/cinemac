package pl.com.bottega.cinemac.model.reservation;

import pl.com.bottega.cinemac.model.payment.PaymentType;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class PaymentAttempt {

    private String message;
    private boolean successful;

    public PaymentAttempt(PaymentType type, Long cashierId) {
        this.successful = true;
        this.message = "Transaction type: " + type + " | "
                + "Transaction date: " + LocalDateTime.now() + " | "
                + "Cashier Id: " + cashierId;
    }

    PaymentAttempt(){}

    public boolean isSuccessful() {
        return this.successful;
    }

    public String getMessage() {
        return message;
    }
}
