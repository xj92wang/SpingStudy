package springstudy.springdataelastic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import springstudy.springdataelastic.domain.Dataset;
import springstudy.springdataelastic.repository.DatasetRepository;

@Component
public class ElasticService {

    Logger log = LoggerFactory.getLogger(ElasticService.class);

    @Autowired
    ElasticsearchOperations elasticsearchTemplate;

    @Autowired
    DatasetRepository datasetRepository;

    public void createIndex(Class<?> jclass) {
        if(!elasticsearchTemplate.indexOps(jclass).exists()) {

            elasticsearchTemplate.indexOps(jclass).create();
        }else{
            log.info("Index already exists");
        }
    }

    public Dataset indexDocument(Dataset dataset){
        Dataset ret = datasetRepository.save(dataset);
        return ret;
    }

    public Page<Dataset> getByName(String name, Pageable pageRequest){
        return datasetRepository.findByName(name, pageRequest);
    }

    public Page<Dataset> searchAllFields(String name, Pageable pageRequest){

        return datasetRepository.searchAllFieldsUsingCustomQuery(name, pageRequest);
    }

}
