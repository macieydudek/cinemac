package pl.com.bottega.cinemac.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
class Pricing {

    @Id
    @GeneratedValue
    Long id;

    @ElementCollection
    private Map<String, BigDecimal> pricing;

    public Pricing(Map<String, BigDecimal> pricing) {
        this.pricing = pricing;
    }

    public Map<String, BigDecimal> getPricing() {
        return pricing;
    }

    Pricing() {
    }

    void update(Map<String, BigDecimal> pricing) {
        if (pricing == null) {
            pricing = new HashMap<>();
        }
        this.pricing.putAll(pricing);
    }

}
