package hoteldelluna.springweb.jpaPractice.service;

import hoteldelluna.springweb.jpaPractice.entity.*;
import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import hoteldelluna.springweb.jpaPractice.repository.JpaItemRepository;
import hoteldelluna.springweb.jpaPractice.repository.JpaMemberRepository;
import hoteldelluna.springweb.jpaPractice.repository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JpaOrderService {

    private final JpaMemberRepository jpaMemberRepository;
    private final JpaOrderRepository jpaOrderRepository;
    private final JpaItemRepository jpaItemRepository;


    //현재 한번에 하나의 상품만 주문 가능 -> 여러개 주문가능하게 만들어볼 수 있을까?
    @Transactional
    public Long order(Long memberId , Long itemId, int count) {

        //엔티티 조회
        JpaMember jpaMember = jpaMemberRepository.findOne(memberId);
        JpaItem jpaItem = jpaItemRepository.findOne(itemId);

        //배송정보 생성
        JpaDelivery jpaDelivery = new JpaDelivery();
        jpaDelivery.setJpaAddress(jpaMember.getJpaAddress()); //홈페이지에서 받아서하지않나..?
        jpaDelivery.setStatus(JpaDeliveryStatus.READY);

        //주문 상품 생성
        JpaOrderItem jpaOrderItem = JpaOrderItem.createOrderItem(jpaItem, jpaItem.getPrice(), count);
        //주문 생성
        JpaOrder jpaOrder = JpaOrder.createOrder(jpaMember, jpaDelivery, jpaOrderItem);

        //주문 저장
        jpaOrderRepository.save(jpaOrder);
        return jpaOrder.getId();
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        JpaOrder jpaOrder = jpaOrderRepository.findOne(orderId);
        jpaOrder.cancel();
    }

    //주문 검색
    public List<JpaOrder> findOrders(JpaOrderSearch jpaOrderSearch) {
        return jpaOrderRepository.findAllByString(jpaOrderSearch);
    }
}
