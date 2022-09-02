package hoteldelluna.springweb.jpaShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ShopOrderItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ShopItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private ShopOrder order;

    private int orderPrice;

    private int count;

//    private LocalDateTime regTime;

//    private LocalDateTime updateTime;

    public static ShopOrderItem createOrderItem(ShopItem item , int count) {
        ShopOrderItem orderItem = new ShopOrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count); // 예외 발생할 수 있음.
        return orderItem;

    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }
}
