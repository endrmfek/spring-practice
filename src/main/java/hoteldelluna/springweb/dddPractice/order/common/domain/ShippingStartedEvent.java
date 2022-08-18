package hoteldelluna.springweb.dddPractice.order.common.domain;

public class ShippingStartedEvent {
    private String orderNumber;

    public ShippingStartedEvent(String number) {
        this.orderNumber = number;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
