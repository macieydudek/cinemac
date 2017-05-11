package pl.com.bottega.cinemac.model.commands;


import pl.com.bottega.cinemac.model.payment.CreditCard;
import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;

import java.math.BigDecimal;

public class CollectPaymentCommand implements Validatable {
    private ReservationNumber reservationNumber;
    private PaymentType type;
    private Long cashierId;
    private CreditCard creditCard;
    private BigDecimal amount;

    public void setReservationNumber(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public ReservationNumber getReservationNumber() {
        return reservationNumber;
    }

    public PaymentType getType() {
        return type;
    }

    public Long getCashierId() {
        return cashierId;
    }

    @Override
    public void validate(ValidationErrors errors) {
        validateType(errors);
        if (type.equals(PaymentType.CASH)) {
            validateCashierId(errors);
        } else if (type.equals(PaymentType.CREDIT_CARD)) {
            validateCreditCard(errors);
        }
    }

    private void validateCreditCard(ValidationErrors errors) {
        if (creditCard == null) {
            errors.add("credit card", "Has to be defined");
        }
        validateCardNumber(errors);
        validateExpirationMonth(errors);
        validateExpirationYear(errors);
        validateCvc(errors);
    }

    private void validateCvc(ValidationErrors errors) {
        if (creditCard.getExpirationYear() == null) {
            errors.add("credit card - cvc", "Has to be defined");
        }
    }

    private void validateExpirationYear(ValidationErrors errors) {
        if (creditCard.getExpirationYear() == null) {
            errors.add("credit card - expiration year", "Has to be defined");
        }
    }

    private void validateExpirationMonth(ValidationErrors errors) {
        if (creditCard.getExpirationMonth() == null) {
            errors.add("credit card - expiration month", "Has to be defined");
        }
    }

    private void validateCardNumber(ValidationErrors errors) {
        if (isEmpty(creditCard.getNumber())) {
            errors.add("credit card - name", "Has to be defined");
        }
    }

    private void validateCashierId(ValidationErrors errors) {
        if (cashierId == null) {
            errors.add("cashierId", "Has to be defined");
        }
    }

    private void validateType(ValidationErrors errors) {
        if (type == null) {
            errors.add("type", "Has to be defined");
        }
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
