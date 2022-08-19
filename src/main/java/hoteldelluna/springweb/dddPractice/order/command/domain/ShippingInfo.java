package hoteldelluna.springweb.dddPractice.order.command.domain;


import hoteldelluna.springweb.dddPractice.common.model.Address;
import lombok.Getter;

import javax.persistence.*;

@Embeddable // 값 타입
@Getter
public class ShippingInfo {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code")),
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_addr2"))
    })
    private Address address;

    private String message;

    @Embedded
    private Receiver receiver;

    public ShippingInfo() {}

    public ShippingInfo(Address address, String message, Receiver receiver) {
        this.address = address;
        this.message = message;
        this.receiver = receiver;
    }
}
