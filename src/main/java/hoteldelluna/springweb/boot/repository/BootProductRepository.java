package hoteldelluna.springweb.boot.repository;

import hoteldelluna.springweb.boot.entity.BootProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootProductRepository extends JpaRepository<BootProduct , Long> {

}
