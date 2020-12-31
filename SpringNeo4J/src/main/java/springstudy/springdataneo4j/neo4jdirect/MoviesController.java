package springstudy.springdataneo4j.neo4jdirect;

import org.neo4j.driver.Session;
import org.springframework.boot.actuate.autoconfigure.metrics.export.stackdriver.StackdriverPropertiesConfigAdapter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.neo4j.driver.Driver;

import java.util.List;

//@RestController
public class MoviesController {

    private final Driver driver;
    public MoviesController(Driver driver) {
        this.driver = driver;
    }

    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMovieTitles() {

        try (Session session = driver.session()) {
            return session.run("MATCH (p:Person) -[a:ACTED_IN]->(m:Movie) RETURN p,a,m ORDER BY m.name ASC")
                    .list(r -> r.get("m").asNode().get("title").asString() + " " + r.get("a").asRelationship().get("roles").asList() + " is played by " + r.get("p").asNode().get("name").asString());
        }
    }

}
