package pl.com.bottega.cinemac.model.reservation;

import pl.com.bottega.cinemac.model.payment.ChargeResults;
import pl.com.bottega.cinemac.model.payment.PaymentType;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Embeddable
public class PaymentAttempt {

    private String message;

    private Boolean successful;

    private PaymentAttempt(Boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }


    public static PaymentAttempt createCCPaymentAttempt(ChargeResults chargeResults) {
        Boolean successful = chargeResults.getSuccessful();
        String message = "Transaction type: CREDIT CARD | " +
                "Transaction time: " + LocalDateTime.now() +
                addStatusMessage(chargeResults) +
                addErrorMessage(chargeResults);
        return new PaymentAttempt(successful, message);
    }

    private static String addErrorMessage(ChargeResults chargeResults) {
        String errorMessage = "";
        if (!chargeResults.getSuccessful()) {
            errorMessage += " Reason: " + chargeResults.getMessage();
        }
        return errorMessage;
    }

    private static String addStatusMessage(ChargeResults chargeResults) {
        String message = " Status: ";
        if (chargeResults.getSuccessful()) {
            message += "SUCCESS | Payment number: " + chargeResults.getMessage();
        } else {
            message += "FAILED";
        }
        return message;
    }

    public static PaymentAttempt createCashPaymentAttempt(Long cashierId) {
        String message = "Transaction type: CASH | "
                + "Transaction time: " + LocalDateTime.now() + " | "
                + "Cashier Id: " + cashierId;
        return new PaymentAttempt(true, message);
    }

    PaymentAttempt() {
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public String getMessage() {
        return message;
    }
}
