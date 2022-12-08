package hoteldelluna.springweb.boot.service;

import hoteldelluna.springweb.boot.dto.BootProductDto;
import hoteldelluna.springweb.boot.dto.BootProductResponseDto;

public interface BootProductService {
    BootProductResponseDto getProduct(Long number);

    BootProductResponseDto saveProduct(BootProductDto productDto);

    BootProductResponseDto changeProductName(Long number , String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
