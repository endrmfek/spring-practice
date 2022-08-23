package hoteldelluna.springweb.dddPractice.order.command.application;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.Product;
import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.ProductId;
import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.ProductRepository;
import hoteldelluna.springweb.dddPractice.common.ValidationError;
import hoteldelluna.springweb.dddPractice.common.ValidationErrorException;
import hoteldelluna.springweb.dddPractice.order.command.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceOrderService { // 실제 주문 서비스
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrdererService ordererService; //인터페이스. 구현체 어딨어 -> infra 단에 있음.

    public PlaceOrderService(ProductRepository productRepository, OrderRepository orderRepository, OrdererService ordererService) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.ordererService = ordererService;
    }

    public OrderNo placeOrder(OrderRequest orderRequest) { //요청들어오면 주문실행
        List<ValidationError> errors = validateOrderRequest(orderRequest); // 요청에 대한 검증
        if(!errors.isEmpty()) throw new ValidationErrorException(errors);

        List<OrderLine> orderLines = new ArrayList<>();
        for(OrderProduct op : orderRequest.getOrderProducts()) {
            Optional<Product> productOpt = productRepository.findById(new ProductId(op.getProductId()));
            Product product = productOpt.orElseThrow(() -> new NoOrderProductException(op.getProductId()));
            orderLines.add(new OrderLine(product.getId() , product.getPrice() , op.getQuantity()));
        }
        OrderNo orderNo = orderRepository.nextOrderNo();
        Orderer orderer = ordererService.createOrderer(orderRequest.getOrdererMemberId());

        Order order = new Order(orderNo, orderer, orderLines, orderRequest.getShippingInfo(), OrderState.PAYMENT_WAITING);
        orderRepository.save(order);
        return orderNo;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }


}
