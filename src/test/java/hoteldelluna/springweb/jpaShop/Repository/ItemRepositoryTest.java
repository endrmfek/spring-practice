package hoteldelluna.springweb.jpaShop.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import hoteldelluna.springweb.jpaShop.entity.QShopItem;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ShopItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void 상품저장테스트() throws Exception{
        // given
        ShopItem item = new ShopItem(); //setter넣기 싫다..
        item.setItemNm("테스트상품");
        item.setPrice(10000);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setItemDetail("테스트상품 상세설명");
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        // when
        ShopItem savedItem = itemRepository.save(item);
        // then
        System.out.println(savedItem);
    }

    public void createItemList() {
        for(int i=1; i<=10; i++) {
            ShopItem item = new ShopItem(); //setter넣기 싫다..
            item.setItemNm("테스트상품 " + i);
            item.setPrice(10000 + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            ShopItem savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNm() throws Exception{
        this.createItemList();
        List<ShopItem> itemList = itemRepository.findByItemNm("테스트 상품 1");
        for(ShopItem item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("QueryDsl Test")
    public void queryDsl() throws Exception{
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QShopItem qitem = QShopItem.shopItem;
        JPAQuery<ShopItem> query = queryFactory.selectFrom(qitem)
                .where(qitem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qitem.itemDetail.like("%" + "테스트 상품 상세 설명" + " %"))
                .orderBy(qitem.price.desc());

        List<ShopItem> itemList = query.fetch();

        for(ShopItem item : itemList) {
            System.out.println("아이템 목록 = " + item.toString());
        }
    }
}