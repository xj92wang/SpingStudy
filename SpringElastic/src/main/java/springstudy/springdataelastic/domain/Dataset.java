package springstudy.springdataelastic.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dataset")
public class Dataset {

    @Id
    String id;

    String name;

    String description;

    String domain;


    public String toString() {
      return "Id:" + id + " name: "+ name + " domain: " + domain + " desciprion:" + description;
    }

    public String getId() {
        return getDomain() + "." + getName();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
