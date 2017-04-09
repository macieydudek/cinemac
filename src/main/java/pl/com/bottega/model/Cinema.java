package pl.com.bottega.model;

import pl.com.bottega.model.commands.CreateCinemaCommand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    public Cinema(CreateCinemaCommand cmd) {
        this.name = cmd.getName();
        this.city = cmd.getCity();
    }

    Cinema(){}

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
