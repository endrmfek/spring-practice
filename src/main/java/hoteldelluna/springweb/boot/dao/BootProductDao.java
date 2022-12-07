package hoteldelluna.springweb.boot.dao;

import hoteldelluna.springweb.boot.entity.BootProduct;

public interface BootProductDao {

    BootProduct insertProduct(BootProduct product);

    BootProduct selectProduct(Long number);

    BootProduct updateProduct(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
