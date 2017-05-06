package pl.com.bottega.cinemac.model.payment;

import java.math.BigDecimal;

public interface PaymentFacade {

    ChargeResults charge(CreditCard cc, BigDecimal amount);
}
