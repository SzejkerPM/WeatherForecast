package api.weatherApi.jsonToJava;

import lombok.Data;

import java.util.List;

@Data
public class WeatherMaster {

    private String base;
    private int visibility;
    private long dt;
    private int timezone;
    private long id;
    private String name;
    private int cod;
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private Rain rain;
    private Snow snow;

}
