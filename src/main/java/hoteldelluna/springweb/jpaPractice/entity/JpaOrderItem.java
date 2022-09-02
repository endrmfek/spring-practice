package hoteldelluna.springweb.jpaPractice.entity;

import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jpa_order_item")
@Getter @Setter
public class JpaOrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "item_id")// 연관관계 주인은 many쪽에서..
    private JpaItem jpaItem;

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계 주인은 many쪽에서..
    @JoinColumn(name = "order_id")
    private JpaOrder jpaOrder;

    private int orderPrice;
    private int count;

    //생성메서드
    public static JpaOrderItem createOrderItem(JpaItem jpaItem, int orderPrice, int count) {
        JpaOrderItem jpaOrderItem = new JpaOrderItem();
        jpaOrderItem.setJpaItem(jpaItem);
        jpaOrderItem.setOrderPrice(orderPrice);
        jpaOrderItem.setCount(count);

        jpaItem.removeStock(count); // item의 개수 줄여줘야됨.
        return jpaOrderItem;
    }

    //비지니스 로직
    public void cancel() {
        this.getJpaItem().addStock(count);
    }

    //조회 로직
    //주문 상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
