package pl.com.bottega.cinemac.model.commands;


import pl.com.bottega.cinemac.model.payment.PaymentType;
import pl.com.bottega.cinemac.model.reservation.ReservationNumber;

public class CollectPaymentCommand implements Validatable {
    private ReservationNumber reservationNumber;
    private PaymentType type;
    private Long cashierId;

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
}
