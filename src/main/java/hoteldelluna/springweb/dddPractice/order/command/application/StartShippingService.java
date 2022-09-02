package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.common.VersionConflictException;
import hoteldelluna.springweb.dddPractice.order.NoOrderException;
import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderNo;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StartShippingService {
    private OrderRepository orderRepository;

    public StartShippingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void startShipping(StartShippingRequest req) {
        Optional<Order> orderOpt = orderRepository.findByNumber(new OrderNo(req.getOrderNumber()));
        Order order = orderOpt.orElseThrow(NoOrderException::new);
        if(order.matchVersion(req.getVersion())) {
            throw new VersionConflictException();
        }
        order.startShipping();
    }
}
