package hoteldelluna.springweb.boot.repository.support;

import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.entity.QBootProduct;
import hoteldelluna.springweb.boot.repository.support.BootProductRepositoryCustom;
import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.ProductRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootProductRepositoryCustomImpl extends QuerydslRepositorySupport implements BootProductRepositoryCustom {

    public BootProductRepositoryCustomImpl() {
        super(BootProduct.class);
    }


    @Override
    public List<BootProduct> findByName(String name) {
        QBootProduct product = QBootProduct.bootProduct;

        List<BootProduct> productList = from(product)
                .where(product.name.eq(name))
                .select(product)
                .fetch();

        return productList;
    }
}
