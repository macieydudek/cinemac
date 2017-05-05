package pl.com.bottega.cinemac.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CalculationResult {

    private Set<CalculationItem> tickets;
    private BigDecimal totalPrice;

    CalculationResult(Set<CalculationItem> tickets) {
        this.tickets = tickets;
        this.totalPrice = calculateTotalPrice(tickets);
    }

    private BigDecimal calculateTotalPrice(Set<CalculationItem> tickets) {
        BigDecimal result = BigDecimal.ZERO;
        for (CalculationItem calculationItem : tickets) {
            result = result.add(calculationItem.getTotalPrice());
        }
        return result;
    }


    public Set<CalculationItem> getTickets() {
        if (tickets == null) {
            this.tickets = new HashSet<>();
        }
        return tickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
