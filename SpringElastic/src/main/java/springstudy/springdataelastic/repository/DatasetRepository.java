package springstudy.springdataelastic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import springstudy.springdataelastic.domain.Dataset;

public interface DatasetRepository extends ElasticsearchRepository<Dataset, String> {
    Page<Dataset> findByName(String name, Pageable pageable);

    @Query("{\"multi_match\": {\n" +
            "      \"query\":    \"?0\",\n" +
            "      \"fields\": [ \"*\" ]     }}")
    Page<Dataset> searchAllFieldsUsingCustomQuery(String query, Pageable pageable);
}
