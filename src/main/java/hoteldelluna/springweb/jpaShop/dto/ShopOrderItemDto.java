package hoteldelluna.springweb.jpaShop.dto;

import hoteldelluna.springweb.jpaShop.entity.ShopOrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopOrderItemDto { //아아템 이미지URL?

    private String itemNm;
    private int count;
    private int orderPrice;
    private String imgUrl;

    public ShopOrderItemDto(ShopOrderItem orderItem, String imgUrl) {
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

}
