package api.weatherApi.jsonToJava;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherMaster {

    @JsonProperty("id")
    private long cityId;
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("coord")
    private Coord cityCoordinates;
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
