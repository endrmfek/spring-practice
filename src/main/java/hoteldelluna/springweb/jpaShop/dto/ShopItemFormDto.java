package hoteldelluna.springweb.jpaShop.dto;

import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShopItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer StockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ShopItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public ShopItem createItem() {
        return modelMapper.map(this, ShopItem.class);
    }

    public static ShopItemFormDto of(ShopItem item) {
        return modelMapper.map(item, ShopItemFormDto.class);
    }
}
