package hoteldelluna.springweb.boot.repository.support;

import hoteldelluna.springweb.boot.entity.BootProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BootProductRepositorySupport")
public interface BootProductRepository extends JpaRepository<BootProduct , Long> , BootProductRepositoryCustom {
}
