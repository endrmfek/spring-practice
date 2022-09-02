package hoteldelluna.springweb.jpaShop.entity;

import hoteldelluna.springweb.jpaShop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop_orders")
@Getter @Setter
public class ShopOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") //'다' 쪽에서 연관관계 주인
    private ShopMember member;

    private LocalDateTime orderDate; //주문일

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) //필드 이름으로 매핑하나?
    private List<ShopOrderItem> orderItems = new ArrayList<>(); //양방향.

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public void addOrderItem(ShopOrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static ShopOrder createOrder(ShopMember member, List<ShopOrderItem> orderItemList) {
        ShopOrder order = new ShopOrder();
        order.setMember(member);
        for (ShopOrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (ShopOrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        for(ShopOrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
