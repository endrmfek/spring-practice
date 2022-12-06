package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.jpaShop.Repository.ShopItemImgRepository;
import hoteldelluna.springweb.jpaShop.Repository.ShopItemRepository;
import hoteldelluna.springweb.jpaShop.dto.ShopItemFormDto;
import hoteldelluna.springweb.jpaShop.dto.ShopItemImgDto;
import hoteldelluna.springweb.jpaShop.dto.ShopItemSearchDto;
import hoteldelluna.springweb.jpaShop.dto.ShopMainItemDto;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import hoteldelluna.springweb.jpaShop.entity.ShopItemImg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ShopItemService {

    private final ShopItemImgRepository itemImgRepository;
    private final ShopItemRepository itemRepository;
    private final ShopItemImgService itemImgService;

    public Long saveItem(ShopItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        //상품 등록
        ShopItem item = itemFormDto.createItem();
        itemRepository.save(item);

        for(int i=0; i<itemImgFileList.size(); i++) {
            ShopItemImg itemImg = new ShopItemImg();
            itemImg.setItem(item);
            if(i==0) { // 첫번째이미지를 썸네일(?) 로
                itemImg.setRepimgYn("Y");
            } else {
                itemImg.setRepimgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ShopItemFormDto getItemDtl(Long itemId) { // itemDetail
        //상품 상세 화면
        log.info("아이템 아이디 넘어오냐 = {}" , itemId);
        List<ShopItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId); // list
        log.info("아이템 이미지 넘어오냐 = {}" , itemImgList);
        List<ShopItemImgDto> itemImgDtoList = new ArrayList<>(); // dto
        for (ShopItemImg itemImg : itemImgList) {
            ShopItemImgDto itemImgDto = ShopItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        ShopItem item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new); //엔티티를
        ShopItemFormDto itemFormDto = ShopItemFormDto.of(item); //DTO로 변경
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        System.out.println("사이즈 몇이냐" + itemFormDto.getItemImgDtoList().size());
        return itemFormDto;
    }

    public Long updateItem(ShopItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        ShopItem item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> itemImgIds = itemFormDto.getItemImgIds();
        for(int i=0; i<itemImgIds.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<ShopItem> getAdminItemPage(ShopItemSearchDto itemSearchDto , Pageable pageable) {
        //단순 위임임
       return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<ShopMainItemDto> getMainItemPage(ShopItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }
}
