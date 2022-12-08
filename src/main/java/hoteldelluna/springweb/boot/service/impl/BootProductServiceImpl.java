package hoteldelluna.springweb.boot.service.impl;

import hoteldelluna.springweb.boot.dao.BootProductDao;
import hoteldelluna.springweb.boot.dto.BootProductDto;
import hoteldelluna.springweb.boot.dto.BootProductResponseDto;
import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.service.BootProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BootProductServiceImpl implements BootProductService {

    private final BootProductDao bootProductDao;

    @Autowired
    public BootProductServiceImpl(BootProductDao bootProductDao) {
        this.bootProductDao = bootProductDao;
    }

    @Override
    public BootProductResponseDto getProduct(Long number) {
        BootProduct product = bootProductDao.selectProduct(number);

        //entity -> dto로 변환.
        //이거 빌더패턴이나 앤티티객체나 DTO 객체 내부에 변한메서드를 추가해서 간단하게 전환 가능.
        BootProductResponseDto productResponseDto = new BootProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public BootProductResponseDto saveProduct(BootProductDto productDto) {
        //dto -> 엔티티로 변경해서 넣어야돼.
        BootProduct product = new BootProduct();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        BootProduct savedProduct = bootProductDao.insertProduct(product);

        BootProductResponseDto productResponseDto = new BootProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public BootProductResponseDto changeProductName(Long number, String name) throws Exception {
        BootProduct changedProduct = bootProductDao.updateProduct(number, name);

        BootProductResponseDto productResponseDto = new BootProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        //검증메서드?
        bootProductDao.deleteProduct(number);
    }
}
