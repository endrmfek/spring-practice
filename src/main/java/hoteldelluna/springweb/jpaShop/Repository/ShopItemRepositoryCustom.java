package hoteldelluna.springweb.jpaShop.Repository;


import hoteldelluna.springweb.jpaShop.dto.ShopItemSearchDto;
import hoteldelluna.springweb.jpaShop.dto.ShopMainItemDto;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 1. 사용자 정의 인터페이스 작성.
public interface ShopItemRepositoryCustom {
    Page<ShopItem> getAdminItemPage(ShopItemSearchDto itemSearchDto , Pageable pageable);

    Page<ShopMainItemDto> getMainItemPage(ShopItemSearchDto itemSearchDto, Pageable pageable);
}
