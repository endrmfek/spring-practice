package hoteldelluna.springweb.dddPractice.order.common.domain;

import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public interface OrderRepository extends Repository<Order, OrderNo> {
    Optional<Order> findByNumber(OrderNo number); // 미쳤다.

    void save(Order order);

    default OrderNo nextOrderNo() {
        int randomNo = ThreadLocalRandom.current().nextInt(900000) + 100000;
        String number = String.format("%tY%<tm%<td%<tH-%d" , new Date() , randomNo);
        return new OrderNo(number);
    }

}
