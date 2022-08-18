package hoteldelluna.springweb.dddPractice.order.common.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderNo implements Serializable { // 타입으로 넘겨야되서 직렬화
    @Column(name="order_number")
    private String number;

    protected OrderNo() {} // 기본생성자 원래는 필요없지만 jpa때문에 생성. -> protected로 최소 노출

    public OrderNo(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object o) { // 좀 더 공부해야될듯.. 잘모르겠습니다.
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        OrderNo orderNo = (OrderNo) o;
        return Objects.equals(number, orderNo.number);
    }

    public static OrderNo of(String number) { // OrderNo.of(number) -> 새로운 객체 생성
        return new OrderNo(number);
    }
}
