package hoteldelluna.springweb.dddPractice.catalog.query.product;

import hoteldelluna.springweb.dddPractice.catalog.command.domain.category.CategoryId;
import hoteldelluna.springweb.dddPractice.catalog.command.domain.product.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductDataDao extends Repository<ProductData , ProductId> {
    Optional<ProductData> findById(ProductId id);

    Page<ProductData> findByCategoryIdsContains(CategoryId id , Pageable pageable);
}
