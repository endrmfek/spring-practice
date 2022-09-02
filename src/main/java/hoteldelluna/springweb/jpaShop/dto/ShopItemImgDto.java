package hoteldelluna.springweb.jpaShop.dto;

import hoteldelluna.springweb.jpaShop.entity.ShopItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ShopItemImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ShopItemImgDto of(ShopItemImg itemImg) {
        return modelMapper.map(itemImg, ShopItemImgDto.class);
    }
}
