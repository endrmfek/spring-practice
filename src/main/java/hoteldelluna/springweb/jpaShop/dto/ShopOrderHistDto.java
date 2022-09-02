package hoteldelluna.springweb.jpaShop.dto;

import hoteldelluna.springweb.jpaShop.constant.OrderStatus;
import hoteldelluna.springweb.jpaShop.entity.ShopOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShopOrderHistDto { //주문정보
    private Long orderId;
    private String orderDate;
    private OrderStatus orderStatus;
    private List<ShopOrderItemDto> orderItemDtoList = new ArrayList<>();

    public ShopOrderHistDto(ShopOrder order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(ShopOrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);
    }
}
