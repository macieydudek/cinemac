package pl.com.bottega.model.commands;

public class CreateCinemaCommand {
    private String name;
    private String city;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
