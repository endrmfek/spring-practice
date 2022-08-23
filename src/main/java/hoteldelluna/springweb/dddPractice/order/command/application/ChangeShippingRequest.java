package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.order.command.domain.ShippingInfo;
import lombok.Getter;

@Getter
public class ChangeShippingRequest { // 단순 DTO . DTO 는 응용 계층에 위치
    private String number;
    private ShippingInfo shippingInfo;

    public ChangeShippingRequest(String number , ShippingInfo shippingInfo) {
        this.number = number;
        this.shippingInfo = shippingInfo;
    }

}
