package hoteldelluna.springweb.dddPractice.order.common.domain;

import hoteldelluna.springweb.dddPractice.common.jpa.MoneyConverter;
import hoteldelluna.springweb.dddPractice.common.model.Money;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//주문 - 애그리거트 루트
@Entity
@Table(name = "purchase_order")
@Access(AccessType.FIELD) //필드로 바로접근하겠다.
@Getter
public class Order {
    @EmbeddedId
    private OrderNo number; // 식별자 -> order_number (PK)

    @Version
    private long version; // 비동기 처리부분

    @Embedded
    private Orderer orderer; //주문자

    @ElementCollection(fetch = FetchType.LAZY) // 지연로딩
    @CollectionTable(name = "order_line" , joinColumns = @JoinColumn(name="order_number"))
    @OrderColumn(name = "line_idx") //순서대로 넣을게?
    private List<OrderLine> orderLines; // 주문라인

    @Convert(converter = MoneyConverter.class)
    @Column(name="total_amounts")
    private Money totalAmounts; //총 가격

    @Embedded
    private ShippingInfo shippingInfo; //배송정보

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderState state; //주문 상태

    @Column(name ="order_date")
    private LocalDateTime orderDate; //주문 날짜

    protected Order() {}

    public Order(OrderNo number, Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        setNumber(number);
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.state = state;
        this.orderDate = LocalDateTime.now();
        //이벤트 처리?
    }

    private void setNumber(OrderNo number) {
        if(number == null) throw new IllegalArgumentException("no number"); //검증
        this.number = number;
    }

    private void setOrderer(Orderer orderer) {
        if(orderer == null) throw new IllegalArgumentException("no orderer"); //검증
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines); // 검증
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if(orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no orderLine");
        }
    }
    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream()
                .mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if(shippingInfo == null) throw new IllegalArgumentException("no shipping info");
        this.shippingInfo = shippingInfo;
    }

    /*도메인 요구사항*/

    //배송정보 변경
    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped(); //아직 배송전이여야됨. (요구사항)
        setShippingInfo(newShippingInfo);
        //이벤트
    }

    //주문 취소
    public void cancel() {
        verifyNotYetShipped(); //아직 배송전이여야됨. (요구사항)
        this.state = OrderState.CANCELED;
        //이벤트
    }

    private void verifyNotYetShipped() {
        if(!isNotYetShipped()) {
            throw new AlreadyShippedException();
        }
    }

    private boolean isNotYetShipped() {
        //결제대기 , 상품준비때만 취소나 배송지변경이 가능합니다.
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public boolean matchVersion(long version) {
        return this.version == version;
    }

    public void startShipping() {
        verifyShippableState();
        this.state = OrderState.SHIPPED;
        //이벤트 발생
    }

    private void verifyShippableState() {
        verifyNotYetShipped();
        verifyNotCanceled();
    }

    private void verifyNotCanceled() {
        if(state == OrderState.CANCELED) {
            throw new OrderAlreadyCanceledException();
        }
    }


}
