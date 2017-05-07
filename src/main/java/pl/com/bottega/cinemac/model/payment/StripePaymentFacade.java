package pl.com.bottega.cinemac.model.payment;

import java.math.BigDecimal;

public class StripePaymentFacade implements PaymentFacade {
    @Override
    public ChargeResults charge(CreditCard cc, BigDecimal amount) {
        return null;
    }
}
