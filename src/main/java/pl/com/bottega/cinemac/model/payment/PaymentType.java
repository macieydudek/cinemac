package pl.com.bottega.cinemac.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType {

    CASH, CREDIT_CARD;

    private static Map<String, PaymentType> paymentTypes = new HashMap<>();

    static {
        paymentTypes.put("cash", CASH);
        paymentTypes.put("credit_card", CREDIT_CARD);
    }

    @JsonCreator
    public static PaymentType forValue(String value) {
        return paymentTypes.get(value);
    }
}
