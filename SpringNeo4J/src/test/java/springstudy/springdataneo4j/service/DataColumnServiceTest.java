package springstudy.springdataneo4j.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springstudy.springdataneo4j.domain.DataColumn;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class DataColumnServiceTest {
    @Autowired
    DataColumnService dataColumnService;


    @Test
    public void testNewDataColumnRelationship2() {

        DataColumn r1 = new DataColumn("eapp", "raw","account", "r_account_id");
        DataColumn r2 = new DataColumn("eapp", "raw","account", "r_account_name");

        DataColumn s1 = new DataColumn("eapp", "sanitized","account", "s_account_id");
        DataColumn s2 = new DataColumn("eapp", "sanitized","account", "s_account_name");

        dataColumnService.newDataColumnRelationship(s1, r1);
        dataColumnService.newDataColumnRelationship(s2, r2);

        DataColumn c1 = new DataColumn("eapp", "curated","account", "c_account_id");
        DataColumn c2 = new DataColumn("eapp", "curated","account", "c_account_name");
        DataColumn c3 = new DataColumn("eapp", "curated","account", "c_account_name_id");

        dataColumnService.newDataColumnRelationship(c1, s1);
        dataColumnService.newDataColumnRelationship(c2, s2);
        dataColumnService.newDataColumnRelationship(c3, s1);
        dataColumnService.newDataColumnRelationship(c3, s2);

    }


    @Test
    public void testSearchParentDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findDerivedByColumn("c_account_name_id");
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }


    @Test
    public void testSearchChildrenDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findImpactedByColumn("r_account_id");
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }

    @Test
    public void testFindDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findByColumn("account");
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }

    @Test
    public void testFindAllDerivedDataColumn() {
        Collection<DataColumn> li = dataColumnService.findAllDerivedDataColumn();
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }
    @Test
    public void testFindDataColumn() {
        Optional<DataColumn> a = dataColumnService.findDataColumn("eapp.curated.account.c_account_name_id");
        System.out.println(a.get());
    }

    @Test
    public void testFindAll() {
        Iterable<DataColumn> li = dataColumnService.findAll();
        for (DataColumn d : li) {
            System.out.println(d);
        }

    }

    @Test void testFindAllDataStoreLineage(){
        List<Map<String, Object>> li = dataColumnService.findAllDatasetLineage();
        for (Map<String,Object> d : li) {
            System.out.println(d.get("s_datastore")+"."+d.get("s_dataset")+"->"+d.get("t_datastore")+"."+ d.get("t_dataset"));
        }

    }

    @Test
    public void testDeleteAll() {
        dataColumnService.deleteAll();
    }

}