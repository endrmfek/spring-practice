package hoteldelluna.springweb.jpaPractice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="jpaOrders") // 없을 시 클래스명으로 테이블 생성. but order는 예약어 때문에 orders를 사용.
@Getter
@Setter
public class JpaOrder {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // xToOne -> 지연로딩
    @JoinColumn(name = "member_id") // <- 외래키 지정 , 연관관계의 주인.
    private JpaMember jpaMember;

    @OneToMany(mappedBy = "jpaOrder", cascade = CascadeType.ALL) //orderItem.order
    private List<JpaOrderItem> jpaOrderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private JpaDelivery jpaDelivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private JpaOrderStatus status;

    //연관관계 메서드
    public void setJpaMember(JpaMember jpaMember) {
        this.jpaMember = jpaMember;
        jpaMember.getJpaOrders().add(this);
    }

    public void addOrderItem(JpaOrderItem jpaOrderItem) {
        jpaOrderItems.add(jpaOrderItem);
        jpaOrderItem.setJpaOrder(this);
    }

    public void setJpaDelivery(JpaDelivery jpaDelivery) {
        this.jpaDelivery = jpaDelivery;
        jpaDelivery.setJpaOrder(this);
    }

    //생성메서드
    public static JpaOrder createOrder(JpaMember jpaMember, JpaDelivery jpaDelivery, JpaOrderItem... jpaOrderItems) {
        JpaOrder jpaOrder = new JpaOrder();
        jpaOrder.setJpaMember(jpaMember);
        jpaOrder.setJpaDelivery(jpaDelivery);
        for(JpaOrderItem jpaOrderItem : jpaOrderItems) {
            jpaOrder.addOrderItem(jpaOrderItem);
        }
        jpaOrder.setStatus(JpaOrderStatus.ORDER);
        jpaOrder.setOrderDate(LocalDateTime.now());
        return jpaOrder;
    }

    //비지니스 로직
    //주문 취소
    public void cancel() {
        if(jpaDelivery.getStatus() == JpaDeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능 합니다.");
        }

        this.setStatus(JpaOrderStatus.CANCEL);
        for(JpaOrderItem jpaOrderItem : jpaOrderItems) {
            jpaOrderItem.cancel();
        }
    }

    //조회로직
    //전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (JpaOrderItem jpaOrderItem : jpaOrderItems) {
            totalPrice += jpaOrderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
