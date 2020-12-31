package springstudy.springdataneo4j.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springstudy.springdataneo4j.domain.DataColumn;

import java.util.Collection;
import java.util.Optional;

@SpringBootTest
class DataColumnServiceTest {
    @Autowired
    DataColumnService dataColumnService;


    @Test
    public void testNewDataColumnRelationship() {
        DataColumn iso = new DataColumn("eapp", "table0", "col1");
        dataColumnService.saveDataColumn(iso);

        DataColumn a = new DataColumn("eapp", "table1", "col1");
        DataColumn b = new DataColumn("eapp", "table2", "col2");
        a = dataColumnService.newDataColumnRelationship(a, b);
        System.out.println(a);
    }

    @Test
    public void testNewDataColumnRelationship2() {

        DataColumn a = new DataColumn("eapp", "table1", "col1");
        DataColumn a1 = new DataColumn("eapp", "table1", "col1_a");

        DataColumn b = new DataColumn("eapp", "table2", "col2");
        DataColumn c = new DataColumn("eapp", "table3", "col3");


        dataColumnService.newDataColumnRelationship(a1, b);
        dataColumnService.newDataColumnRelationship(a, b);
        dataColumnService.newDataColumnRelationship(b, c);

    }


    @Test
    public void testSearchParentDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findDerivedByColumn("col1_a");
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }


    @Test
    public void testSearchChildrenDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findImpactedByColumn("col3");
        for (DataColumn d : li) {
            System.out.println(d);
        }
    }

    @Test
    public void testFindDataColumnByColumn() {
        Collection<DataColumn> li = dataColumnService.findByColumn("col1");
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
        Optional<DataColumn> a = dataColumnService.findDataColumn("eapp.table1.col1");
        System.out.println(a.get());
    }

    @Test
    public void testFindAll() {
        Iterable<DataColumn> li = dataColumnService.findAll();
        for (DataColumn d : li) {
            System.out.println(d);
        }

    }

    @Test
    public void testDeleteAll() {
        dataColumnService.deleteAll();
    }

}