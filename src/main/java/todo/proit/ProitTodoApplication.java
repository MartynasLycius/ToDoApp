package todo.proit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("todo.proit.persistence.repository")
@SpringBootApplication
public class ProitTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProitTodoApplication.class, args);
	}

}
