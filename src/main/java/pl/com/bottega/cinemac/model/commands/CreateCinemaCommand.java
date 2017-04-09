package pl.com.bottega.cinemac.model.commands;

public class CreateCinemaCommand implements Validatable{
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

    @Override
    public void validate(ValidationErrors errors) {
        if (isEmpty(name)) {
            errors.add("name", "can't be blank");
        }
        if (isEmpty(city)) {
            errors.add("city", "can't be blank");
        }
    }
}
