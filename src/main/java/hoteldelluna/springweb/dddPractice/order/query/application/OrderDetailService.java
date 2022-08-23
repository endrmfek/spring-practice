package hoteldelluna.springweb.dddPractice.order.query.application;

import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductData;
import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductQueryService;
import hoteldelluna.springweb.dddPractice.order.command.domain.Order;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderLine;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderNo;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDetailService {
    private OrderRepository orderRepository;
    private ProductQueryService productQueryService;

    public OrderDetailService(OrderRepository orderRepository, ProductQueryService productQueryService) {
        this.orderRepository = orderRepository;
        this.productQueryService = productQueryService;
    }

    public Optional<OrderDetail> getOrderDetail(String orderNumber) {
        Optional<Order> orderOpt = orderRepository.findByNumber(new OrderNo(orderNumber));
        return orderOpt.map(order -> {
            List<OrderLineDetail> orderLines = order.getOrderLines().stream()
                    .map(orderLine -> {
                        Optional<ProductData> productOpt = productQueryService.getProduct(orderLine.getProductId().getId());
                        return new OrderLineDetail(orderLine, productOpt.orElse(null));
                    }).collect(Collectors.toList());
            return new OrderDetail(order, orderLines);
        });

    }
}
