package repository.jsonToJava;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CityObject {

    @JsonProperty("id")
    private int cityId;
    @JsonProperty("name")
    private String cityName;
}
