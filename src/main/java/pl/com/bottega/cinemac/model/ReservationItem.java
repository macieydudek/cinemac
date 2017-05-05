package pl.com.bottega.cinemac.model;

public class ReservationItem {

    private String kind;
    private Long count;

    public Long getCount() {
        return count;
    }

    public String getKind() {
        return kind;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
