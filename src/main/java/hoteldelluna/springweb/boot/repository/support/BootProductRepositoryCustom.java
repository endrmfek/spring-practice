package hoteldelluna.springweb.boot.repository.support;

import hoteldelluna.springweb.boot.entity.BootProduct;
import hoteldelluna.springweb.boot.repository.BootProductRepository;

import java.util.List;

public interface BootProductRepositoryCustom {
    List<BootProduct> findByName(String name);
}
