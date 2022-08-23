package hoteldelluna.springweb.dddPractice.order.ui;

import hoteldelluna.springweb.dddPractice.order.query.application.OrderDetailService;
import hoteldelluna.springweb.dddPractice.order.query.dao.OrderSummaryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyOrderController {
    private OrderDetailService orderDetailService;
    private OrderSummaryDao orderSummaryDao;

    public MyOrderController(OrderDetailService orderDetailService, OrderSummaryDao orderSummaryDao) {
        this.orderDetailService = orderDetailService;
        this.orderSummaryDao = orderSummaryDao;
    }

    @RequestMapping("/ddd/my/orders")
    public String orders(ModelMap modelMap) {
        return "ddd/my/orders";
    }

    @RequestMapping("/ddd/my/orders/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        return "ddd/my/orderDetail";
    }


}
