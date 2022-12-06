package hoteldelluna.springweb.jpaShop.controller;

import hoteldelluna.springweb.jpaShop.dto.ShopItemFormDto;
import hoteldelluna.springweb.jpaShop.dto.ShopItemSearchDto;
import hoteldelluna.springweb.jpaShop.entity.ShopItem;
import hoteldelluna.springweb.jpaShop.service.ShopItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopItemController {

    private final ShopItemService itemService;

    /*
    * item 추가
    * admin 권한을 가진 사람만
    * */
    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto" , new ShopItemFormDto());
        return "shop/item/itemForm";
    }

    @PostMapping(value="/admin/item/new")
    public String itemNew(@ModelAttribute("itemFormDto") @Valid ShopItemFormDto itemFormDto , BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "shop/item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "shop/item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e){
            // 폴더 생성 안해놓으면 바로 오류나옴.
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "shop/item/itemForm";
        }

        return "redirect:/shop/";
    }

    /*
    * item 수정
    * admin 권한을 가진 사람만.
    * */
    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable Long itemId, Model model) {
        try {
            ShopItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage" , "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto", new ShopItemFormDto());
            return "shop/item/itemForm";
        }

        return "shop/item/itemForm";
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid @ModelAttribute("itemFormDto") ShopItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {
        if(bindingResult.hasErrors()) {
            log.debug("errors = {}" , bindingResult);
            return "shop/item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId()==null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "shop/item/itemForm";
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 오류가 발생했습니다.");
            return "shop/item/itemForm";
        }

        return "redirect:/shop/";
    }

    /*
    * 상품 관리 페이지.
    * */
    @GetMapping(value = {"/admin/items" , "/admin/items/{page}"})
    public String itemManage(@ModelAttribute("itemSearchDto") ShopItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page , Model model) {
        Pageable pageable = PageRequest.of(page.orElse(0),3); // (페이지 , 사이즈)
        Page<ShopItem> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "shop/item/itemMng";
    }

    /*
    * 아이템 보기
    * 일반 사용자
    * */
    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        //itemimgdtolist
        ShopItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "shop/item/itemDtl";
    }

}
