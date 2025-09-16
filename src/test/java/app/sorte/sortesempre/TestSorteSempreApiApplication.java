package app.sorte.sortesempre;

import org.springframework.boot.SpringApplication;

public class TestSorteSempreApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(SorteSempreApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
