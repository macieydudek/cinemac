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

    Pricing() {
        this.pricing = new HashMap();
    }

    void update(Map<String, BigDecimal> pricing) {
        this.pricing.putAll(pricing);
    }

}
