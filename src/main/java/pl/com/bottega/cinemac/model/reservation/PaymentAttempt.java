package pl.com.bottega.cinemac.model.reservation;

import java.util.Set;

public class PaymentAttempt {

    private Set<String> paymentErrors;

    public boolean isSuccessful() {
        return paymentErrors.isEmpty();
    }
}
