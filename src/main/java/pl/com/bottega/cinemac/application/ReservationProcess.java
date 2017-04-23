package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.CalculationResult;
import pl.com.bottega.cinemac.model.commands.CalculatePriceCommand;

public interface ReservationProcess {


    CalculationResult calculatePrice(CalculatePriceCommand cmd);
}
