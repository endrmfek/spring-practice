package hoteldelluna.springweb.jpaPractice.repository;

import hoteldelluna.springweb.jpaPractice.entity.item.JpaItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaItemRepository {

    private final EntityManager em;

    public void save(JpaItem jpaItem){
        if(jpaItem.getId() == null) {
            em.persist(jpaItem);
        } else {
            em.merge(jpaItem); // item이 있으면 merge?
        }
    }

    public JpaItem findOne(Long id) {
        return em.find(JpaItem.class , id);
    }

    public List<JpaItem> findAll() {
        return em.createQuery("select i from JpaItem i" , JpaItem.class)
                .getResultList();
    }
}
