package pl.com.bottega.cinemac.model;

import javax.persistence.Column;
import javax.persistence.MapKey;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Pricing {

    @MapKey(name = "ticket type")
    @Column(name = "price")
    Map<String, BigDecimal> pricing;

    Pricing(Map<String, BigDecimal> pricing) {
        this.pricing = new HashMap<String, BigDecimal>();
        this.pricing.putAll(pricing);
    }

    Pricing() {

    }
}
