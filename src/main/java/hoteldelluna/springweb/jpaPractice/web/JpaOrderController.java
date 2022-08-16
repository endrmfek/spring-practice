package hoteldelluna.springweb.jpaPractice.web;

import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrderSearch;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrder;
import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import hoteldelluna.springweb.jpaPractice.service.JpaItemService;
import hoteldelluna.springweb.jpaPractice.service.JpaMemberService;
import hoteldelluna.springweb.jpaPractice.service.JpaOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JpaOrderController {

    private final JpaOrderService jpaOrderService;
    private final JpaMemberService jpaMemberService;
    private final JpaItemService jpaItemService;


    //주문하기
    @GetMapping(value = "/jpa/order")
    public String createForm(Model model) {
        List<JpaMember> jpaMembers = jpaMemberService.findMembers();
        List<JpaItem> jpaItems = jpaItemService.findItems();

        model.addAttribute("jpaMembers" , jpaMembers);
        model.addAttribute("jpaItems", jpaItems);

        return "jpa/order/orderForm";
    }

    @PostMapping(value= "/jpa/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        jpaOrderService.order(memberId, itemId, count);
        return "redirect:/jpa/orders";
    }

    //주문 목록 검색
    @GetMapping(value="/jpa/orders")
    public String orderList(@ModelAttribute("jpaOrderSearch") JpaOrderSearch jpaOrderSearch, Model model) {
        List<JpaOrder> jpaOrders = jpaOrderService.findOrders(jpaOrderSearch);
        model.addAttribute("jpaOrders", jpaOrders);

        return "jpa/order/orderList";
    }

    @PostMapping(value="/jpa/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        jpaOrderService.cancelOrder(orderId);

        return "redirect:/jpa/orders";
    }
}
