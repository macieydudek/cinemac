package pl.com.bottega.cinemac.model;

import java.math.BigDecimal;

public class CalculationItem {

    String kind;
    Long count;

    BigDecimal unitPrice;
    BigDecimal totalPrice;

    public String getKind() {
        return kind;
    }

    public Long getCount() {
        return count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public CalculationItem(ReservationItem reservationItem, BigDecimal unitPrice) {
        this.kind = reservationItem.getKind();
        this.count = reservationItem.getCount();
        this.unitPrice = unitPrice;
        this.totalPrice = calculateTotalPrice(reservationItem.getCount(), unitPrice);
    }

    private BigDecimal calculateTotalPrice(Long count, BigDecimal unitPrice) {
        return unitPrice.multiply(BigDecimal.valueOf(count));
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}