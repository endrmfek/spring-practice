package hoteldelluna.springweb.jpaPractice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 클래스 이름과 동일한 테이블 생성
@Getter
@Setter
public class JpaMember {

    @Id @GeneratedValue
    @Column(name = "member_id") // 이거 지정 안하면 디폴트가 필드값. 다르게 지정하려면 name속성 필수.
    private Long id;

    private String name;

    @Embedded
    private JpaAddress jpaAddress;

    @OneToMany(mappedBy = "jpaMember") // 회원은 주문을 여러개 할 수 있다. -> oneToMany // order.member
    private List<JpaOrder> jpaOrders = new ArrayList<>();

}
