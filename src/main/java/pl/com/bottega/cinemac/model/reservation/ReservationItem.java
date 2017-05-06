package pl.com.bottega.cinemac.model.reservation;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservationItem {

    @Column(name = "number")
    public Long count;

    private String kind;

    ReservationItem(){}

    public ReservationItem(Long count, String kind) {
        this.count = count;
        this.kind = kind;
    }

    public Long getCount() {
        return count;
    }

    public String getKind() {
        return kind;
    }

}
