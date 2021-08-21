package API.weatherApi.jsonToJava;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {

    @JsonProperty("1h")
    private double rainPerH;
}
