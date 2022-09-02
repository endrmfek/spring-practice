package hoteldelluna.springweb.jpaShop.controller;

import hoteldelluna.springweb.jpaShop.dto.ShopItemSearchDto;
import hoteldelluna.springweb.jpaShop.dto.ShopMainItemDto;
import hoteldelluna.springweb.jpaShop.service.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopMainController {

    private final ShopItemService itemService;

    @GetMapping("/")
    public String main(@ModelAttribute("itemSearchDto") ShopItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        Page<ShopMainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "shop/main";
    }


}
