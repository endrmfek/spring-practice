package hoteldelluna.springweb.jpaPractice.repository;

import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaMemberRepository {

    @PersistenceContext
    EntityManager em;

    public void save(JpaMember jpaMember) {
        em.persist(jpaMember); // persist == insert ?
    }

    public JpaMember findOne(Long id) {
        return em.find(JpaMember.class , id);
    }

    public List<JpaMember> findAll() {
        return em.createQuery("select m from JpaMember m" , JpaMember.class)
                .getResultList();
    }

    public List<JpaMember> findByName(String name) {
        return em.createQuery("select m from JpaMember m where m.name = :name" , JpaMember.class)
                .setParameter("name" , name)
                .getResultList();
    }

}
