package hoteldelluna.springweb.jpaPractice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JpaOrderSearch {

    private String memberName; //회원 이름 순으로
    private JpaOrderStatus jpaOrderStatus; //주문 상태 [order, cancel]

}
