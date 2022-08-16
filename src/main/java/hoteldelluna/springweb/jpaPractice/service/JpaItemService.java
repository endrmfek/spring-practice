package hoteldelluna.springweb.jpaPractice.service;

import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import hoteldelluna.springweb.jpaPractice.repository.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JpaItemService {

    //상품 서비스는 상품 레포지토리에 단순 위임만 하는 클래스. -> 왜?...
    private final JpaItemRepository jpaItemRepository;

    @Transactional
    public void saveItem(JpaItem jpaItem) {
        jpaItemRepository.save(jpaItem);
    }

    @Transactional
    public void updateItem(Long id, String name, int price) {
        JpaItem jpaItem = jpaItemRepository.findOne(id);
        jpaItem.setName(name);
        jpaItem.setPrice(price);
    }

    public List<JpaItem> findItems() {
        return jpaItemRepository.findAll();
    }

    public JpaItem findOne(Long itemId) {
        return jpaItemRepository.findOne(itemId);
    }

}
