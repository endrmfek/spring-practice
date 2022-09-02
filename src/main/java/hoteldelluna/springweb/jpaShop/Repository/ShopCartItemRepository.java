package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.dto.ShopCartDetailDto;
import hoteldelluna.springweb.jpaShop.entity.ShopCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCartItemRepository extends JpaRepository<ShopCartItem, Long> {
    ShopCartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new hoteldelluna.springweb.jpaShop.dto.ShopCartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from ShopCartItem ci, ShopItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc ")
    List<ShopCartDetailDto> findByCartDetailDtoList(@Param("cartId")Long cartId);
}
