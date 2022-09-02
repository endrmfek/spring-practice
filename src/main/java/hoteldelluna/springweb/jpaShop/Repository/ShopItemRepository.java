package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ShopItemRepository extends JpaRepository<ShopItem , Long>, QuerydslPredicateExecutor<ShopItem>, ShopItemRepositoryCustom {
    List<ShopItem> findByItemNm(String itemNm);

    @Query("select i from ShopItem i where i.itemDetail like %:itemDetail% order by i.price desc ")
    List<ShopItem> findByItemDetail(String itemDetail);
}
