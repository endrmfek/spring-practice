package hoteldelluna.springweb.dddPractice.common.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Email {
    private String address;

    public Email(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    public static Email of(String address) {
        return new Email(address);
    }
}
