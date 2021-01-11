package springstudy.springdataneo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstudy.springdataneo4j.domain.DataColumn;
import springstudy.springdataneo4j.repository.DataColumnRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DataColumnService {
    @Autowired
    DataColumnRepository dataColumnRepository;

    public void deleteAll() {
        dataColumnRepository.deleteAll();
    }

    public Iterable<DataColumn> findAll(){
        return dataColumnRepository.findAll();
    }

    public Optional<DataColumn> findDataColumn(String id){
        return dataColumnRepository.findById(id);
    }

    public Collection<DataColumn> findDerivedByColumn(String col_exp){
        return dataColumnRepository.findDerivedByColumn(col_exp);
    }

    public Collection<DataColumn> findImpactedByColumn(String col_exp){
        return dataColumnRepository.findImpactedByColumn(col_exp);
    }

    public Collection<DataColumn> findAllDerivedDataColumn(){
        return dataColumnRepository.findAllDerived();
    }

    public Collection<DataColumn> findByColumn(String col_name){
        return dataColumnRepository.findByColumn(col_name);
    }

    public void saveDataColumn(DataColumn a){
        dataColumnRepository.save(a);
    }

    public List<Map<String,Object>> findAllDatasetLineage(){
        return dataColumnRepository.findDatasetLineage();
    }

    public DataColumn newDataColumnRelationship(DataColumn a, DataColumn b) {
        Optional<DataColumn> a_tmp = dataColumnRepository.findById(a.getId());
        if(!a_tmp.isPresent()) {
            dataColumnRepository.save(a);
        }
        Optional<DataColumn> b_tmp = dataColumnRepository.findById(b.getId());
        if(!b_tmp.isPresent()) {
            dataColumnRepository.save(b);
        }
        a.derivedFrom(b);
        dataColumnRepository.save(a);
        return a;
    }

}
