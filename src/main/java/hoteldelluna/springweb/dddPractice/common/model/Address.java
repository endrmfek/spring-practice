package hoteldelluna.springweb.dddPractice.common.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String zipCode;

    private String address1;

    private String address2;

    public Address() {}

    public Address(String zipCode, String address1, String address2) {
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }
}
