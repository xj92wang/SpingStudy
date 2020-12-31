package springstudy.springdataneo4j.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.*;
import java.util.stream.Collectors;

@NodeEntity
public class DataColumn {
    public String getId() {
        return id;
    }

    @Id
    private String id;

    private String domain;
    private String dataset;
    private String column;

    private DataColumn() {

    }

    public DataColumn(String domain, String dataset, String column) {
        this.column = column;
        this.dataset = dataset;
        this.domain = domain;
        id = domain + "." + getFullName();
    }

    @Relationship(type = "DERIVED", direction = Relationship.INCOMING)
    public Set<DataColumn> deriveddatacolumnset;

    public void derivedFrom(DataColumn col) {
        if (null == deriveddatacolumnset) {
            deriveddatacolumnset = new HashSet<>();
        }
        deriveddatacolumnset.add(col);
    }

    public String getColumn() {
        return column;
    }

    public String getFullName() {
        return dataset + "." + column;
    }

    public String toString() {
        return getFullName() + "'s derived from =>"
                + Optional.ofNullable(this.deriveddatacolumnset).orElse(
                Collections.emptySet()).stream().map(DataColumn::getFullName)
                .collect(Collectors.toList());
    }
}
