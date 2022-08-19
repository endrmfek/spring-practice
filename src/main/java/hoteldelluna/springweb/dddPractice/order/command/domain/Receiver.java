package hoteldelluna.springweb.dddPractice.order.command.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // 값 타입
@Getter
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Column(name="receiver_phone")
    private String phone;

    public Receiver(){}

    public Receiver(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
