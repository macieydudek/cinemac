package pl.com.bottega.cinemac.application.implementation;

import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.PriceCalculator;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

public class StandardReservationProcess implements ReservationProcess {

    PriceCalculator priceCalculator;

    public StandardReservationProcess(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public CalculationResult calculatePrice(CalculatePriceCommand cmd) {
        return priceCalculator.calculatePrice(cmd);
    }
}
