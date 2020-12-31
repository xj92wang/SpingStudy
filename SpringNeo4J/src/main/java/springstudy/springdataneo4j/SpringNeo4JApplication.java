package springstudy.springdataneo4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class SpringNeo4JApplication {
	private final static Logger log = LoggerFactory.getLogger(SpringNeo4JApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringNeo4JApplication.class, args);
	}


}
