package pl.com.bottega.cinemac.application;

import pl.com.bottega.cinemac.model.commands.Validatable;
import pl.com.bottega.cinemac.model.reservation.ReservationStatus;

public class ReservationsQuery implements Validatable {

    String query;

    String status;

    public void setQuery(String query) {
        this.query = query;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuery() {
        return query;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public void validate(ValidationErrors errors) {
        try {
            if (isEmpty(query))
                errors.add("query", "can't be blank");
            if (isEmpty(status))
                errors.add("status", "can't be blank");
            else
                ReservationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            errors.add("status", "invalid reservation status");
        }
    }

}
