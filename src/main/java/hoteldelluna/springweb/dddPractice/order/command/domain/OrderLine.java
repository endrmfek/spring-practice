package hoteldelluna.springweb.dddPractice.order.command.domain;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.ProductId;
import hoteldelluna.springweb.dddPractice.common.jpa.MoneyConverter;
import hoteldelluna.springweb.dddPractice.common.model.Money;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class OrderLine {
    @Embedded
    private ProductId productId;

    @Convert(converter = MoneyConverter.class) // DB엔 Money라는 타입 없다. Integer로 바꿔야됨. Converter 구현 할것.
    @Column(name = "price")
    private Money price; //가격

    private int quantity; //개수

    @Convert(converter = MoneyConverter.class)
    private Money amounts; // 총 가격

    protected OrderLine() {};

    public OrderLine(ProductId productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }


}
