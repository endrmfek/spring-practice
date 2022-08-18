package hoteldelluna.springweb.dddPractice.order.common.domain;

import hoteldelluna.springweb.dddPractice.common.event.Event;

public class OrderCanceledEvent extends Event {
    private String orderNumber;

    public OrderCanceledEvent(String number) {
        super();
        this.orderNumber = number;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
