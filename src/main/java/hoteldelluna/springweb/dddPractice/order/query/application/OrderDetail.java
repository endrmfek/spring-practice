package hoteldelluna.springweb.dddPractice.order.query.application;

import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderState;
import hoteldelluna.springweb.dddPractice.order.command.domain.Orderer;
import hoteldelluna.springweb.dddPractice.order.command.domain.ShippingInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDetail {
    private final String number;
    private long version;
    private final Orderer orderer;
    private final ShippingInfo shippingInfo;
    private final OrderState state;
    private final int totalAmounts;
    private List<OrderLineDetail> orderLines;
    private final boolean notYetShipped;

    public OrderDetail(Order order , List<OrderLineDetail> orderLines) {
        this.orderLines = orderLines;
        number = order.getNumber().getNumber();
        version = order.getVersion();
        orderer = order.getOrderer();
        shippingInfo = order.getShippingInfo();
        state = order.getState();
        notYetShipped = order.isNotYetShipped();
        totalAmounts = order.getTotalAmounts().getValue();
    }

}
