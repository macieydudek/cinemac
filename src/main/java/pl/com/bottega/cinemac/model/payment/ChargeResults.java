package pl.com.bottega.cinemac.model.payment;

import com.stripe.model.Charge;

public class ChargeResults {


    private final String message;
    private final Boolean successful;

    public ChargeResults(Charge charge) {
        this.message = charge.getId();
        this.successful = charge.getPaid();
    }

    public ChargeResults(String message) {
        this.message = message;
        this.successful = false;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
