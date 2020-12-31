package springstudy.springdataelastic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import springstudy.springdataelastic.domain.Dataset;

@SpringBootTest
@ComponentScan(basePackages = {"springstudy.springdataelastic.app",
        "springstudy.springdataelastic.service"
})
public class ElasticServiceTest {

    @Autowired
    ElasticService elasticService;

    @Test
    public void testCreateIndex() {
        elasticService.createIndex(Dataset.class);

    }

    @Test
    public void testIndexDocument() {
        Dataset dataset = new Dataset();
        dataset.setId("id01");
        dataset.setDomain("eapp");
        dataset.setName("dataset_new");
        dataset.setDescription("This is dataset after update");

        Dataset ds = elasticService.indexDocument(dataset);

    }

    @Test
    void testGetByName() {
        Pageable pageRequest = PageRequest.of(0, 2);

        Page<Dataset> ret = elasticService.getByName("dataset1", pageRequest);
        while (!ret.isEmpty()) {
            pageRequest = pageRequest.next();
            for (Dataset dataset : ret) {
                System.out.println(dataset);
            }
            System.out.println("search next page");
            ret = elasticService.getByName("dataset1", pageRequest);
        }
    }

    @Test
    void testSearchAllFields() {
        Pageable pageRequest = PageRequest.of(0, 2);

        Page<Dataset> ret = elasticService.searchAllFields("id01", pageRequest);
        while (!ret.isEmpty()) {
            pageRequest = pageRequest.next();
            for (Dataset dataset : ret) {
                System.out.println(dataset);
            }
            System.out.println("search next page");
            ret = elasticService.searchAllFields("id01", pageRequest);
        }
    }
}
