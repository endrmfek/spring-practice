package hoteldelluna.springweb;

import hoteldelluna.springweb.dddPractice.common.jpa.RangeableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = RangeableRepositoryImpl.class)
//@EnableAsync
public class SpringwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringwebApplication.class, args);
	}

}
