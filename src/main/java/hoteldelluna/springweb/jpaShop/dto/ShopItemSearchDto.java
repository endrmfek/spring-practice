package hoteldelluna.springweb.jpaShop.dto;

import hoteldelluna.springweb.jpaShop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemSearchDto {

    /*
    * 현재 시간과 상품 등록일을 비교해서 상품 데이터 조회
    */
    private String searchDateType;
    /*
    * 판매 상태에 따라 조회
    */
    private ItemSellStatus searchSellStatus;
    /*
    * 어떤 유형으로 조회할지? (상품명, 상품등록자 아이디)
    * */
    private String searchBy;
    /*
    * 검색어 저장 변수
    * */
    private String searchQuery = "";
}
