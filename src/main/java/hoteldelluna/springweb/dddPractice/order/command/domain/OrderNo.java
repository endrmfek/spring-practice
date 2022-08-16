package hoteldelluna.springweb.dddPractice.order.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderNo {
    @Column(name="order_number")
    private String number;
}
