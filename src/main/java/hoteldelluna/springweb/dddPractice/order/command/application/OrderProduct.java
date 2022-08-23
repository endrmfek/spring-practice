package hoteldelluna.springweb.dddPractice.order.command.application;

import lombok.Getter;

@Getter
public class OrderProduct { //상품 주문 DTO
    private String productId;
    private int quantity;

    public OrderProduct() {}

    public OrderProduct(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
