package pl.com.bottega.cinemac.model.commands;

import java.math.BigDecimal;
import java.util.Map;

public class DefineMoviePricingCommand implements Validatable {

    private Long movieId;
    private Map<String, BigDecimal> prices;

    public DefineMoviePricingCommand(Long movieId, Map<String, BigDecimal> prices) {

        this.movieId = movieId;
        this.prices = prices;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (movieId == null) {
            errors.add("movie", "Has to be defined");
        }
        if (!prices.containsKey("regular")) {
            errors.add("pricing", "Price for >>regular<< ticket has to be defined");
        }
        if (!prices.containsKey("student")) {
            errors.add("pricing", "Price for >>student<< ticket has to be defined");
        }
        validatePrices(errors);
    }

    private void validatePrices(ValidationErrors errors) {
        for (String ticketType : prices.keySet()) {
            if (prices.get(ticketType).compareTo(BigDecimal.ZERO) == -1) {
                errors.add("Ticket price", "ticket price cannot be negative");
            }
        }
    }

    public Long getMovieId() {
        return movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }
}
