package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PriceCalculator {

    private ShowingRepository showingRepository;

    public PriceCalculator(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public CalculationResult calculatePrice(CalculatePriceCommand cmd) {
        Set<CalculationItem> calculationItems = new HashSet<>();
        Showing showing = showingRepository.get(cmd.getShowId());
        Map<String, BigDecimal> pricing = showing.determinePricing().getPricing();
        Set<ReservationItem> tickets = cmd.getTickets();
        for (ReservationItem reservationItem : tickets) {
            String ticketKind = reservationItem.getKind();
            BigDecimal unitPrice = pricing.get(ticketKind);
            CalculationItem calculationItem = new CalculationItem(reservationItem, unitPrice);
            calculationItems.add(calculationItem);
        }
        return new CalculationResult(calculationItems);
    }
}
