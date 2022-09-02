package hoteldelluna.springweb.jpaShop.entity;

import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import hoteldelluna.springweb.jpaShop.dto.ShopItemFormDto;
import hoteldelluna.springweb.jpaShop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shop_item")
@Getter
@Setter
@ToString
public class ShopItem extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id; // 상품 코드

    @Column(nullable = false , length = 50)
    private String itemNm; // 상품명

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime; //등록시간

    private LocalDateTime updateTime; //수정시간

    public void updateItem(ShopItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if(restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }
}
