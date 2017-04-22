package pl.com.bottega.cinemac.application;

import java.math.BigDecimal;
import java.util.Map;

public class PricesDto {

    public Map<String, BigDecimal> getPriceMap() {
        return priceMap;
    }
    Map<String, BigDecimal> priceMap;
}
