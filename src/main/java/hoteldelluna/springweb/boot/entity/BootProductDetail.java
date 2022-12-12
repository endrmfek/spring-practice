package hoteldelluna.springweb.boot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "boot_product_detail")
public class BootProductDetail extends BaseEntity{
    public BootProductDetail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /*
    * 이거 까보면 기본 fetch전략이 default가 eager -> 즉시로딩. (연관객체를 같이 조회함)
    * 그리고 optional default가 true -> 연관객체에 null을 허용함. -> left outer join 갈김.
    * optional 속성을 false로 바꾸면 -> null 허용 x -> inner join 갈김.
    * */
    @OneToOne
    @JoinColumn(name = "product_number") //외래키의 이름
    private BootProduct product;
}
