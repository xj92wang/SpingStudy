package springstudy.springdataneo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import springstudy.springdataneo4j.domain.DataColumn;

import java.util.Collection;
import java.util.Optional;

public interface DataColumnRepository extends Neo4jRepository<DataColumn, String> {
    Optional<DataColumn> findById(String id);

    Collection<DataColumn> findByColumn(String column);

    @Query("match p = (s)-[*]->(l:DataColumn) where NOT ()-->(s) and l.column = $column return p")
    Collection<DataColumn> findDerivedByColumn(String column);

    @Query("match p = (s:DataColumn)-[*]->(l:DataColumn) where NOT (l)-->() and s.column = $column return p")
    Collection<DataColumn> findImpactedByColumn(String column);

    @Query("MATCH p=()-[r:DERIVED]->(m:DataColumn) RETURN p")
    Collection<DataColumn> findAllDerived();


}
