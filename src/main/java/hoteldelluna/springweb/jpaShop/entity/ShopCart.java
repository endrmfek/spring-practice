package hoteldelluna.springweb.jpaShop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "shop_cart")
@Getter
@Setter
@ToString
public class ShopCart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue
    private Long id; // PK

    @OneToOne
    @JoinColumn(name = "member_id") // table에 member_id를 가지고있는거야.
    private ShopMember member; // 사람은 하나의 장바구니를 가지고있다. (FK)

    public static ShopCart createCart(ShopMember member) {
        ShopCart cart = new ShopCart();
        cart.setMember(member);
        return cart;
    }

}
