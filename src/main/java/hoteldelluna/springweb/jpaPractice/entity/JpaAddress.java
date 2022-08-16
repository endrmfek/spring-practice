package hoteldelluna.springweb.jpaPractice.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class JpaAddress {
    private String city;
    private String street;
    private String zipcode;

    protected JpaAddress() {}

    public JpaAddress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
