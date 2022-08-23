package hoteldelluna.springweb.dddPractice.common.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.persistence.Id;
import java.io.Serializable;

@NoRepositoryBean
public interface RangeableRepository<T, ID extends Serializable>
        extends Repository<T, ID>, RangeableExecutor<T> {
}
