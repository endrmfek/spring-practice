package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.order.NoOrderException;
import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderNo;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChangeShippingService {
    private OrderRepository orderRepository;

    public ChangeShippingService(OrderRepository orderRepository) { // 생성자가 하나면 자동 autowired;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void changeShipping(ChangeShippingRequest changeShippingRequest) {
        Optional<Order> orderOpt = orderRepository.findByNumber(new OrderNo(changeShippingRequest.getNumber()));
        Order order = orderOpt.orElseThrow(() -> new NoOrderException());
        order.changeShippingInfo(changeShippingRequest.getShippingInfo());
    }
}
