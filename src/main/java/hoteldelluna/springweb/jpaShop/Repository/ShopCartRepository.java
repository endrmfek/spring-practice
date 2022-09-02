package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopCart;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCartRepository extends JpaRepository<ShopCart, Long> {
    ShopCart findByMemberId(Long memberId);
}
