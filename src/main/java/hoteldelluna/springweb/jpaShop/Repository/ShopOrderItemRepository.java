package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderItemRepository extends JpaRepository<ShopOrderItem , Long> {
}
