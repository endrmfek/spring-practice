package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.jpaShop.Repository.ShopCartItemRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopCartRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopItemRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopMemberRepository;
import hoteldelluna.springweb.jpaShop.dto.ShopCartDetailDto;
import hoteldelluna.springweb.jpaShop.dto.ShopCartItemDto;
import hoteldelluna.springweb.jpaShop.dto.ShopCartOrderDto;
import hoteldelluna.springweb.jpaShop.dto.ShopOrderDto;
import hoteldelluna.springweb.jpaShop.entity.ShopCart;
import hoteldelluna.springweb.jpaShop.entity.ShopCartItem;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import hoteldelluna.springweb.jpaShop.entity.ShopMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopCartService {

    private final ShopItemRepository itemRepository;
    private final ShopMemberRepository memberRepository;
    private final ShopCartItemRepository cartItemRepository;
    private final ShopCartRepository cartRepository;
    private final ShopOrderService orderService;

    public Long addCart(ShopCartItemDto cartItemDto, String email) {
        ShopItem item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        ShopMember member = memberRepository.findByEmail(email);

        ShopCart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null) {
            cart = ShopCart.createCart(member);
            cartRepository.save(cart);
        }

        ShopCartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            ShopCartItem cartItem = ShopCartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<ShopCartDetailDto> getCartList(String email) {
        List<ShopCartDetailDto> cartDetailDtoList = new ArrayList<>();

        ShopMember member = memberRepository.findByEmail(email);
        ShopCart cart = cartRepository.findByMemberId(member.getId());

        if(cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findByCartDetailDtoList(cart.getId());

        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email) {
        ShopMember curMember = memberRepository.findByEmail(email);// 지금 사용자
        ShopCartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        ShopMember savedMember = cartItem.getCart().getMember(); // 장바구니 사용자

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void updateCartItemCount(Long cartItemId, int count) {
        ShopCartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count); // 바뀐다고?..
    }

    public void deleteCartItem(Long cartItemId) {
        ShopCartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    /*
    * 장바구니 주문
    * */
    public Long orderCartItem(List<ShopCartOrderDto> cartOrderDtoList, String email) {
        List<ShopOrderDto> orderDtoList = new ArrayList<>();
        //카트에 있는 아이템 -> 주문아이템으로 넘겨
        for (ShopCartOrderDto cartOrderDto : cartOrderDtoList) {
            //1. 각각의 카트 아이템을
            ShopCartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            //2. 주문으로 옮겨
            ShopOrderDto orderDto = new ShopOrderDto();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);

        }

        Long orderId = orderService.orders(orderDtoList, email);

        //주문완료 됐으면 삭제해야지
        for (ShopCartOrderDto cartOrderDto : cartOrderDtoList) {
            ShopCartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }
        return orderId;
    }
}
