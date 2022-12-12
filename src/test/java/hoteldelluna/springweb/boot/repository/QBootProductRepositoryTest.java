package hoteldelluna.springweb.boot.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.entity.QBootProduct;
import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QBootProductRepositoryTest {

    @Autowired
    QBootProductRepository qBootProductRepository;

    @Test
    public void queryDSLTest1() {
        Predicate predicate = QBootProduct.bootProduct.name.containsIgnoreCase("펜")
                .and(QBootProduct.bootProduct.price.between(1000, 2500));

        Optional<BootProduct> foundProduct = qBootProductRepository.findOne(predicate);

        if(foundProduct.isPresent()) {
            BootProduct product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

    @Test
    public void queryDSLTest2() {
        QBootProduct qBootProduct = QBootProduct.bootProduct;
        Iterable<BootProduct> productList = qBootProductRepository.findAll(
                qBootProduct.name.contains("펜")
                        .and(qBootProduct.price.between(550 , 1500))
        );

        for(BootProduct product : productList){
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
}