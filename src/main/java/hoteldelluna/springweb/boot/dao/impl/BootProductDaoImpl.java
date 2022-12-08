package hoteldelluna.springweb.boot.dao.impl;

import hoteldelluna.springweb.boot.dao.BootProductDao;
import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.repository.BootProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class BootProductDaoImpl implements BootProductDao {

    private final BootProductRepository productRepository;

    @Autowired
    public BootProductDaoImpl(BootProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BootProduct insertProduct(BootProduct product) {
        BootProduct savedProduct = productRepository.save(product);
        //예외처리 , 로그삽입
        return savedProduct;
    }

    @Override
    public BootProduct selectProduct(Long number) {
        //getById vs findById
        BootProduct selectedProduct = productRepository.getById(number);

        return selectedProduct;
    }

    @Override
    public BootProduct updateProduct(Long number, String name) throws Exception {
        Optional<BootProduct> selectedProduct = productRepository.findById(number);

        BootProduct updatedProduct;
        if(selectedProduct.isPresent()) {
            BootProduct product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<BootProduct> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()) {
            BootProduct product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
