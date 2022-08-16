package hoteldelluna.springweb.jpaPractice.web;

import hoteldelluna.springweb.jpaPractice.entity.item.Book;
import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import hoteldelluna.springweb.jpaPractice.service.JpaItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JpaItemController {

    private final JpaItemService jpaItemService;


    //상품 등록
    @GetMapping(value = "/jpa/items/new")
    public String createForm(Model model) {
        model.addAttribute("form" , new JpaBookForm()); //빈 객체
        return "jpa/items/createItemForm";
    }

    //상품 등록
    @PostMapping(value = "/jpa/items/new")
    public String create(JpaBookForm form) {

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        jpaItemService.saveItem(book);
        return "redirect:/jpa/items";
    }

    //상품 목록
    @GetMapping(value="/jpa/items")
    public String list(Model model) {
        List<JpaItem> jpaItems = jpaItemService.findItems();
        model.addAttribute("jpaItems", jpaItems);
        return "jpa/items/itemList";
    }

    //상품 수정
    @GetMapping(value = "/jpa/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) jpaItemService.findOne(itemId); //Book 객체 가져와

        JpaBookForm form = new JpaBookForm(); // DTO로 옮겨
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form" , form);
        return "jpa/items/updateItemForm";
    }

    @PostMapping(value="/jpa/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") JpaBookForm form) {

//        Book book = new Book(); // book을 새로만들잖아.
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        itemService.saveItem(book); -> merge를 사용하면 모든 값이 변해버림.

        jpaItemService.updateItem(form.getId() , form.getName(), form.getPrice()); //변경 감지 사용.
        return "redirect:/jpa/items";
    }

}
