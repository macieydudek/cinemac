package pl.com.bottega.cinemac.model;

import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.pricing.Pricing;
import pl.com.bottega.cinemac.model.showing.Showing;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private Set<Showing> showings;

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

    public Set<Showing> getShowings() {
        return showings;
    }

    public void updatePricing(Map<String, BigDecimal> pricing) {
        if (this.pricing == null) {
            this.pricing = new Pricing(pricing);
        } else {
            this.pricing.update(pricing);
        }
    }

    public Pricing getPricing() {
        return pricing;
    }
}
