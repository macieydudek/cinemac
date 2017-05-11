package pl.com.bottega.cinemac.model.payment;

public class CreditCard {
    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvc;

    public String getNumber() {
        return number;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public String getCvc() {
        return cvc;
    }
}
