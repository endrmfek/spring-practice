package hoteldelluna.springweb.jpaPractice.service;

import hoteldelluna.springweb.jpaPractice.entity.JpaAddress;
import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrder;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrderStatus;
import hoteldelluna.springweb.jpaPractice.entity.item.Book;
import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import hoteldelluna.springweb.jpaPractice.repository.JpaOrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

//상품 주문이 성공해야된다.
//상품을 주문할 때 수량을 초과하면 안된다.
//주문 취소가 성공해야 한다.

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaOrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    JpaOrderService jpaOrderService;
    @Autowired
    JpaOrderRepository jpaOrderRepository;

    @Test
    @DisplayName("상품주문")
    public void 상품주문() throws Exception{
        // given
        JpaMember jpaMember = createMember();
        JpaItem jpaItem = createBook("리얼리티 트랜서핑", 12000, 10);
        int orderCount = 2;

        // when
        Long orderId = jpaOrderService.order(jpaMember.getId() , jpaItem.getId(), orderCount);

        // then
        JpaOrder getJpaOrder = jpaOrderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER" , JpaOrderStatus.ORDER , getJpaOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getJpaOrder.getJpaOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 12000*2 , getJpaOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, jpaItem.getStockQuantity());
    }

    private JpaMember createMember() {
        JpaMember jpaMember = new JpaMember();
        jpaMember.setName("회원1");
        jpaMember.setJpaAddress(new JpaAddress("서울" , "송도문화로", "123-123"));
        em.persist(jpaMember);
        return jpaMember;
    }

    private Book createBook(String name, int price , int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}