package hoteldelluna.springweb.jpaShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="shop_cart_item")
public class ShopCartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private ShopCart cart;

    @OneToOne
    @JoinColumn(name= "item_id")
    private ShopItem item;

    private int count;

    public static ShopCartItem createCartItem(ShopCart cart, ShopItem item, int count) {
        ShopCartItem cartItem = new ShopCartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void updateCount(int count) {
        this.count = count;
    }

}
