package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.jpaShop.Repository.ShopItemImgRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopItemRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopMemberRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopOrderRepository;
import hoteldelluna.springweb.jpaShop.dto.ShopOrderDto;
import hoteldelluna.springweb.jpaShop.dto.ShopOrderHistDto;
import hoteldelluna.springweb.jpaShop.dto.ShopOrderItemDto;
import hoteldelluna.springweb.jpaShop.entity.*;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.GroovyASTTransformationClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopOrderService {

    private final ShopItemRepository itemRepository;
    private final ShopMemberRepository memberRepository;
    private final ShopOrderRepository orderRepository;
    private final ShopItemImgRepository itemImgRepository;

    public Long order(ShopOrderDto shopOrderDto , String email) {
        ShopItem item = itemRepository.findById(shopOrderDto.getItemId()).orElseThrow(EntityNotFoundException::new); //어떤 아이템
        ShopMember member = memberRepository.findByEmail(email); // 어떤 멤버

        List<ShopOrderItem> orderItemList = new ArrayList<>(); //주문 상품 리스트
        ShopOrderItem orderItem = ShopOrderItem.createOrderItem(item, shopOrderDto.getCount()); // 주문상품
        orderItemList.add(orderItem);

        ShopOrder order = ShopOrder.createOrder(member, orderItemList); // 멤버정보에 주문상품 담어.
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<ShopOrderHistDto> getOrderList(String email, Pageable pageable) {
        List<ShopOrder> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countShopOrder(email);

        List<ShopOrderHistDto> orderHistDtos =  new ArrayList<>();

        for(ShopOrder order : orders) {
            ShopOrderHistDto orderHistDto = new ShopOrderHistDto(order);
            List<ShopOrderItem> orderItems = order.getOrderItems();
            for(ShopOrderItem orderItem : orderItems) {
                ShopItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
                ShopOrderItemDto orderItemDto = new ShopOrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }

            orderHistDtos.add(orderHistDto);
        }

        return new PageImpl<ShopOrderHistDto>(orderHistDtos,pageable, totalCount);
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {
        ShopMember curMember = memberRepository.findByEmail(email);
        ShopOrder order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        ShopMember savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
            return false;
        }

        return true;
    }

    public void cancelOrder(Long orderId) {
        ShopOrder order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    /*
    * 장바구니 선택 상품 주문
    * */
    public Long orders(List<ShopOrderDto> orderDtoList, String email) {
        ShopMember member = memberRepository.findByEmail(email);
        List<ShopOrderItem> orderItemList = new ArrayList<>();

        for(ShopOrderDto orderDto : orderDtoList) {
            ShopItem item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);

            ShopOrderItem orderItem = ShopOrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }

        ShopOrder order = ShopOrder.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

}
