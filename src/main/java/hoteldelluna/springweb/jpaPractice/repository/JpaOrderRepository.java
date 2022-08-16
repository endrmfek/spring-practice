package hoteldelluna.springweb.jpaPractice.repository;

import hoteldelluna.springweb.jpaPractice.entity.JpaMember;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrder;
import hoteldelluna.springweb.jpaPractice.entity.JpaOrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepository {
    private final EntityManager em;

    public void save(JpaOrder jpaOrder) {
        em.persist(jpaOrder);
    }

    public JpaOrder findOne(Long id) {
        return em.find(JpaOrder.class , id);
    }

    //주문 엔티티를 저장하고 검색
    //orderSearch에 따라 검색 결과가 달라져. -> 동적 쿼리잖아
    //1. JPQL로 처리
    public List<JpaOrder> findAllByString(JpaOrderSearch jpaOrderSearch) {
        //검색 로직...
        String jpql = "select o from JpaOrder o join o.jpaMember m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if(jpaOrderSearch.getJpaOrderStatus() != null) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(jpaOrderSearch.getMemberName())) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<JpaOrder> query = em.createQuery(jpql, JpaOrder.class)
                .setMaxResults(1000); //최대 1000건

        if(jpaOrderSearch.getJpaOrderStatus() != null) {
            query = query.setParameter("status" , jpaOrderSearch.getJpaOrderStatus());
        }

        if(StringUtils.hasText(jpaOrderSearch.getMemberName())) {
            query = query.setParameter("name" , jpaOrderSearch.getMemberName());
        }

        return query.getResultList();
    }

    public List<JpaOrder> findAllByCriteria(JpaOrderSearch jpaOrderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<JpaOrder> cq = cb.createQuery(JpaOrder.class);
        Root<JpaOrder> o = cq.from(JpaOrder.class);
        Join<JpaOrder, JpaMember> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if(jpaOrderSearch.getJpaOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status") , jpaOrderSearch.getJpaOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if (StringUtils.hasText(jpaOrderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" + jpaOrderSearch.getMemberName() +"%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<JpaOrder> query = em.createQuery(cq)
                .setMaxResults(1000);
        return query.getResultList();
    }
}
