package hoteldelluna.springweb.dddPractice.order.command.domain;

import javax.persistence.*;

@Entity
@Table(name = "purchase_order")
@Access(AccessType.FIELD) //필드로 바로접근하겠다.
public class Order {
    @EmbeddedId
    private OrderNo number; //
}
