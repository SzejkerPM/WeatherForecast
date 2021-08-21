package API.weatherApi.jsonToJava;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Clouds {

    @JsonProperty("clouds")
    private int cloudy;
}
