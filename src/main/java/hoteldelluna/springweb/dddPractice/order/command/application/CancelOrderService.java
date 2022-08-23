package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.order.NoOrderException;
import hoteldelluna.springweb.dddPractice.order.command.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelOrderService {
    private OrderRepository orderRepository;
    private CancelPolicy cancelPolicy; // 정책의 구현체 ㅇㄷ?..

    public CancelOrderService(OrderRepository orderRepository ,
                              CancelPolicy cancelPolicy) {
        this.orderRepository = orderRepository;
        this.cancelPolicy = cancelPolicy;
    }

    @Transactional
    public void cancel(OrderNo orderNo , Canceller canceller) {
        Order order = orderRepository.findByNumber(orderNo)
                .orElseThrow(() -> new NoOrderException());
        if(!cancelPolicy.hasCancellationPermission(order, canceller)) {
            throw new NoCancellablePermission();
        }
        order.cancel();
    }
}
