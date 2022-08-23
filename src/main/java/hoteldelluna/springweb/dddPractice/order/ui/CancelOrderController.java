package hoteldelluna.springweb.dddPractice.order.ui;

import hoteldelluna.springweb.dddPractice.order.command.application.CancelOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CancelOrderController {
    private CancelOrderService cancelOrderService;

    public CancelOrderController(CancelOrderService cancelOrderService) {
        this.cancelOrderService = cancelOrderService;
    }

    @RequestMapping("/ddd/my/orders/{orderNo}/cancel")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        return "ddd/my/orderCanceled";
    }

}
