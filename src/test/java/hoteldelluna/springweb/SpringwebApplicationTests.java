package hoteldelluna.springweb;

import hoteldelluna.springweb.dddPractice.common.jpa.RangeableRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EnableJpaRepositories(repositoryBaseClass = RangeableRepositoryImpl.class)
class SpringwebApplicationTests {

	@Test
	void contextLoads() {
	}

}
