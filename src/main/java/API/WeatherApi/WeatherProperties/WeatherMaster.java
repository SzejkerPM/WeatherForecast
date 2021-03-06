package API.WeatherApi.WeatherProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherMaster {

    @JsonProperty("id")
    private int cityId;
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("coord")
    private Coord cityCoordinates;
    @JsonProperty("cod")
    private int statusCode;
    @JsonProperty("weather")
    private List<Weather> descriptions;
    @JsonProperty("main")
    private Main mainWeatherInfo;
    private Wind wind;
    private Clouds clouds;
    @JsonProperty("sys")
    private Sys country;
    private Rain rain;
    private Snow snow;

}
