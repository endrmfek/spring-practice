package hoteldelluna.springweb.jpaShop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopCartOrderDto { //카트에 있는거 주문하기

    private Long cartItemId; //카트 가져와야지

    private List<ShopCartOrderDto> cartOrderDtoList; //여러개 상품 -> 리스트
}
