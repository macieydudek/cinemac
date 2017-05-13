package pl.com.bottega.cinemac.model.reservation;

import javax.persistence.Embeddable;

@Embeddable

public class Customer {

    private String firstName;
    private String lastName;
    private Long phone;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
