package hoteldelluna.springweb.jpaShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="shop_item_img")
@Getter
@Setter
public class ShopItemImg extends BaseEntity{

    @Id
    @Column(name="item_img_id")
    @GeneratedValue
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private ShopItem item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }


}
