package hoteldelluna.springweb.jpaShop.Repository;

import hoteldelluna.springweb.jpaShop.entity.ShopItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopItemImgRepository extends JpaRepository<ShopItemImg, Long> {
    List<ShopItemImg> findByIdOrderByIdAsc(Long itemId);

    ShopItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);
}
