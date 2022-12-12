package hoteldelluna.springweb.boot.repository;

import hoteldelluna.springweb.boot.entity.BootProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BootProductRepository extends JpaRepository<BootProduct, Long> {

//    @Query("SELECT p from Product AS p where p.name = ?1") -> 파라미터 순서 바뀌면 오류야.
    @Query("SELECT p from BootProduct AS p where p.name = :name")
    List<BootProduct> findByName(@Param("name") String name);

    //엔티티 타입 리턴뿐만 아니라 원하는 칼럼 값만 추출 가능.
    @Query("SELECT p.name , p.price , p.stock from BootProduct AS p where p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
