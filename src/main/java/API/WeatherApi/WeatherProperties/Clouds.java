package API.WeatherApi.WeatherProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Clouds {

    @JsonProperty("all")
    private int cloudy;
}
