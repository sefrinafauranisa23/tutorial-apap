package apap.tutorial.pergipergi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgifyModel {

    @JsonProperty("name")
    public String name;

    @JsonProperty("age")
    public Integer age;

    @JsonProperty("count")
    Integer count;
}