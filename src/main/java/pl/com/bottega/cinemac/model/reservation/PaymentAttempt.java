package pl.com.bottega.cinemac.model.reservation;

import pl.com.bottega.cinemac.model.payment.PaymentType;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class PaymentAttempt {

    private String message;
    private boolean successful;
    private LocalDateTime time;

    public PaymentAttempt(PaymentType type, Long cashierId) {
        this.successful = true;
        this.time = LocalDateTime.now();
        this.message = "SUCCESS";
    }

    public boolean isSuccessful() {
        return this.successful;
    }
}
