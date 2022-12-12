package hoteldelluna.springweb.boot.repository;

import hoteldelluna.springweb.boot.entity.BootProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


public interface QBootProductRepository extends JpaRepository<BootProduct, Long> , QuerydslPredicateExecutor<BootProduct> {

}
