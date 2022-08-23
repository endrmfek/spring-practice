package hoteldelluna.springweb.dddPractice.order.ui;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.Product;
import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductData;
import hoteldelluna.springweb.dddPractice.catalog.query.product.ProductQueryService;
import hoteldelluna.springweb.dddPractice.order.NoOrderException;
import hoteldelluna.springweb.dddPractice.order.command.application.NoOrderProductException;
import hoteldelluna.springweb.dddPractice.order.command.application.OrderProduct;
import hoteldelluna.springweb.dddPractice.order.command.application.OrderRequest;
import hoteldelluna.springweb.dddPractice.order.command.application.PlaceOrderService;
import hoteldelluna.springweb.dddPractice.order.command.domain.OrdererService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private ProductQueryService productQueryService; //상품 조회 서비스
    private PlaceOrderService placeOrderService; // 실제 주문서비스
    private OrdererService ordererService; //인터페이스

    @PostMapping("/ddd/orders/orderConfirm") //주문 확정
    public String orderConfirm(@ModelAttribute("orderReq")OrderRequest orderRequest,
                               ModelMap modelMap){
        return "ddd/order/confirm";
    }

    private void populateProductAndTotalAmountsModel(OrderRequest orderRequest, ModelMap modelMap) {
        List<ProductData> products = getProducts(orderRequest.getOrderProducts()); // 상품정보 가져와.
        modelMap.addAttribute("products", products);
        int totalAmounts = 0;
        for(int i=0; i<orderRequest.getOrderProducts().size(); i++) { // 실제 주문요청에서의 상품.
            OrderProduct op = orderRequest.getOrderProducts().get(i); // 상품 수량
            ProductData prod = products.get(i);
            totalAmounts += op.getQuantity() * prod.getPrice().getValue();
        }
        modelMap.addAttribute("totalAmounts" , totalAmounts);

    }

    private List<ProductData> getProducts(List<OrderProduct> orderProducts) {
        List<ProductData> results = new ArrayList<>();
        for(OrderProduct op : orderProducts) {
            Optional<ProductData> productOpt = productQueryService.getProduct(op.getProductId()); //optional
            ProductData product = productOpt.orElseThrow(() -> new NoOrderProductException(op.getProductId())); //optional에서 꺼내.
            results.add(product);
        }
        return results;
    }

    @PostMapping("/orders/order")
    public String order(@ModelAttribute("orderReq") OrderRequest orderRequest,
                        BindingResult bindingResult,
                        ModelMap modelMap) {
        return "ddd/order/confirm";
    }

    @ExceptionHandler(NoOrderProductException.class)
    public String handleOrderProduct() {
        return "ddd/order/noProduct";
    }

    //Spring Validator를 사용 시 @Valid 어노테이션으로 검증이 필요한 객체를 가져오기 전에 수행할 method를 지정해주는 어노테이션이다
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }



}
