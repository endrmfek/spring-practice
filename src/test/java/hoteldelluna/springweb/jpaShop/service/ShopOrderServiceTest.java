package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.jpaShop.Repository.ShopItemRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopMemberRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopOrderRepository;
import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import hoteldelluna.springweb.jpaShop.dto.ShopOrderDto;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import hoteldelluna.springweb.jpaShop.entity.ShopOrder;
import hoteldelluna.springweb.jpaShop.entity.ShopOrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.yml")
class ShopOrderServiceTest {

    @Autowired
    private ShopOrderService orderService;

    @Autowired
    private ShopOrderRepository orderRepository;

    @Autowired
    private ShopItemRepository itemRepository;

    @Autowired
    private ShopMemberRepository memberRepository;

    public ShopItem saveItem() {
        ShopItem item = new ShopItem();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public ShopMember saveMember() {
        ShopMember member = new ShopMember();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void 주문테스트() throws Exception{
        // given
        ShopItem item = saveItem(); //상품
        ShopMember member = saveMember(); //멤버

        ShopOrderDto orderDto = new ShopOrderDto(); // 주문상품
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail()); // 주문시켰어
        // when

        ShopOrder order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new); // 해당주문 찾아와
        // then
        List<ShopOrderItem> orderItems = order.getOrderItems(); //주문상품 가져와
        int totalPrice = orderDto.getCount()*item.getPrice();

        Assertions.assertEquals(totalPrice, order.getTotalPrice());
    }
}