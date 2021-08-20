package api.weatherApi.jsonToJava;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Snow {

    @JsonProperty("1h")
    private boolean perH;
}
