package pl.com.bottega.cinemac.model.payment;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class StripePaymentFacade implements PaymentFacade {



    @Override
    public ChargeResults charge(CreditCard cc, BigDecimal amount) {

        Stripe.apiKey = "sk_test_YvTq56TAe64dttCHSq9Qgo2H";

        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cc.getNumber());
        cardParams.put("exp_month", cc.getExpirationMonth());
        cardParams.put("exp_year", cc.getExpirationYear());
        cardParams.put("cvc", cc.getCvc());

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", getAmountAsInt(amount));
        chargeParams.put("currency", "pln");
        chargeParams.put("source", cardParams);
        chargeParams.put("description", "Payment for reservation");
        Charge charge = null;
        try {
            charge = Charge.create(chargeParams);
        } catch (CardException e) {
            return new ChargeResults(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ChargeResults(charge);
    }

    private int getAmountAsInt(BigDecimal amount) {
        BigInteger amountAsBigInteger = amount.multiply(BigDecimal.valueOf(100L)).toBigInteger();
        return amountAsBigInteger.intValue();
    }
}
