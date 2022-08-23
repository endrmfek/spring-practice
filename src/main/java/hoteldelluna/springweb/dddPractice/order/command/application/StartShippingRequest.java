package hoteldelluna.springweb.dddPractice.order.command.application;

import lombok.Getter;

@Getter
public class StartShippingRequest { // 불면 DTO.
    private String orderNumber;
    private long version;

    protected StartShippingRequest() {}

    public StartShippingRequest(String orderNumber, long version) {
        this.orderNumber = orderNumber;
        this.version = version;
    }
}
