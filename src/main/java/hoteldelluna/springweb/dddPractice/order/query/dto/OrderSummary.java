package hoteldelluna.springweb.dddPractice.order.query.dto;


import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@Immutable
@Subselect(
        "select o.order_number as number, " +
                "o.version, o.orderer_id, o.orderer_name, o.total_amounts, " +
                "o.receiver_name, o.state, o.order_date, " +
                "p.product_id, p.name as product_name " +
                "from purchase_order o inner join order_line ol " +
                "on o.order_number = ol.order_number " +
                "cross join product p " +
                "where " +
                "ol.line_idx = 0 " +
                "and ol.product_id = p.product_id"

)
@Synchronize({"purchase_order", "order_line", "product"})
public class OrderSummary {
    @Id
    private String number;
    private long version;
    private String ordererId;
    private String ordererName;
    private int totalAmounts;
    private String receiverName;
    private String state;
    private LocalDateTime productId;
    private String productName;
}
