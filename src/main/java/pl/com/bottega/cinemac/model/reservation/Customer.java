package pl.com.bottega.cinemac.model.reservation;

import javax.persistence.Embeddable;

@Embeddable

public class Customer {

    public String firstName;
    public String lastName;
    public Long phone;
    public String email;

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
