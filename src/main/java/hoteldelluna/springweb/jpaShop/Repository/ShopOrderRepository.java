package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopOrderRepository extends JpaRepository<ShopOrder , Long> {
    @Query("select o from ShopOrder o " +
            "where o.member.email = :email " +
            "order by o.orderDate desc ")
    List<ShopOrder> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from ShopOrder o " +
            "where o.member.email = :email ")
    Long countShopOrder(@Param("email") String email);
}
