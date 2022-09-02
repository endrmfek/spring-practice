package hoteldelluna.springweb.jpaShop.Repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import hoteldelluna.springweb.jpaShop.dto.QShopMainItemDto;
import hoteldelluna.springweb.jpaShop.dto.ShopItemSearchDto;
import hoteldelluna.springweb.jpaShop.dto.ShopMainItemDto;
import hoteldelluna.springweb.jpaShop.entity.QShopItem;
import hoteldelluna.springweb.jpaShop.entity.QShopItemImg;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

//2. 구현체 작성
public class ShopItemRepositoryCustomImpl implements ShopItemRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ShopItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QShopItem.shopItem.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QShopItem.shopItem.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("itemNm", searchBy)){
            return QShopItem.shopItem.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QShopItem.shopItem.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }


    // 2. 인터페이스 구현
    @Override
    public Page<ShopItem> getAdminItemPage(ShopItemSearchDto itemSearchDto, Pageable pageable) {
        List<ShopItem> content = queryFactory
                .selectFrom(QShopItem.shopItem)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(),
                                itemSearchDto.getSearchQuery()))
                .orderBy(QShopItem.shopItem.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); //조회 대상 리스트 반환

        long total = queryFactory.select(Wildcard.count).from(QShopItem.shopItem)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne();

        //page 인터페이스의 구현체로 반환.
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression itemNmLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QShopItem.shopItem.itemNm.like("%"+searchQuery+"%");
    }

    @Override
    public Page<ShopMainItemDto> getMainItemPage(ShopItemSearchDto itemSearchDto, Pageable pageable) {
        QShopItem item = QShopItem.shopItem;
        QShopItemImg itemImg = QShopItemImg.shopItemImg;

        List<ShopMainItemDto> content = queryFactory.select(
                        new QShopMainItemDto(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price
                        )
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count)
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
