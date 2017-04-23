package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Integer minAge;
    private Integer length;
    @ElementCollection
    private Set<String> actors;
    @ElementCollection
    private Set<String> genres;

    @OneToOne(cascade = CascadeType.ALL)
    private Pricing pricing;

    Movie() {
    }

    public Movie(CreateMovieCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.minAge = cmd.getMinAge();
        this.length = cmd.getLength();
        this.actors = cmd.getActors();
        this.genres = cmd.getGenres();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<String> getActors() {
        return this.actors;
    }

    public Set<String> getGenres() {
        return this.genres;
    }

    public Integer getMinAge() {
        return this.minAge;
    }

    public Integer getLength() {
        return this.length;
    }

    public void updatePricing(Map<String, BigDecimal> pricing) {
        if (this.pricing == null) {
            this.pricing = new Pricing();
        }
        this.pricing.update(pricing);
    }
}
