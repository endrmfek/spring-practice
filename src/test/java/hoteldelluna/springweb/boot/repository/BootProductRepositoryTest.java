package hoteldelluna.springweb.boot.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.entity.QBootProduct;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BootProductRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BootProductRepository productRepository;

    @org.junit.jupiter.api.Test
    void queryDslTest() {
        JPAQuery<BootProduct> query = new JPAQuery<>(entityManager);
        QBootProduct qBootProduct = QBootProduct.bootProduct;

        List<BootProduct> productList = query
                .from(qBootProduct)
                .where(qBootProduct.name.eq("펜"))
                .orderBy(qBootProduct.price.asc())
                .fetch();

        for(BootProduct product : productList) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product number : " + product.getNumber());
            System.out.println("Product name : " + product.getName());
            System.out.println("Product price : " + product.getPrice());
            System.out.println("Product stock : " + product.getStock());
            System.out.println();
            System.out.println("-------------------");
        }
    }

    @org.junit.jupiter.api.Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QBootProduct qProduct = QBootProduct.bootProduct;

        List<BootProduct> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(BootProduct product : productList) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product number : " + product.getNumber());
            System.out.println("Product name : " + product.getName());
            System.out.println("Product price : " + product.getPrice());
            System.out.println("Product stock : " + product.getStock());
            System.out.println();
            System.out.println("-------------------");
        }
    }

    @org.junit.jupiter.api.Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QBootProduct qProduct = QBootProduct.bootProduct;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            System.out.println("-------------------");
            System.out.println("Product name : " + product);
            System.out.println("-------------------");
        }

        List<Tuple> tupleList = jpaQueryFactory.select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList) {
            System.out.println("-------------------");
            System.out.println("Product name : " + product.get(qProduct.name));
            System.out.println("Product name : " + product.get(qProduct.price));
            System.out.println("-------------------");
        }

    }

    @Autowired
    JPAQueryFactory jpaQueryFactory; // bean으로 만들어두면

    @org.junit.jupiter.api.Test
    void queryDslTest4() {
        //이렇게 초기화 안해도돼 매번
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QBootProduct qProduct = QBootProduct.bootProduct;

        BootProduct product1 = new BootProduct();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        BootProduct product2 = new BootProduct();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        BootProduct product3 = new BootProduct();
        product1.setName("펜");
        product1.setPrice(3000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            System.out.println("-------------------");
            System.out.println("Product name : " + product);
            System.out.println("-------------------");
        }

        List<Tuple> tupleList = jpaQueryFactory.select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList) {
            System.out.println("-------------------");
            System.out.println("Product name : " + product.get(qProduct.name));
            System.out.println("Product name : " + product.get(qProduct.price));
            System.out.println("-------------------");
        }

    }
}