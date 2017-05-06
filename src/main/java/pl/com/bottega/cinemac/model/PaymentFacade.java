package pl.com.bottega.cinemac.model;

import java.math.BigDecimal;

public interface PaymentFacade {

    ChargeResults charge(CreditCard cc, BigDecimal amount);
}
