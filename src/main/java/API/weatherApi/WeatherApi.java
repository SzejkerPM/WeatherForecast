package API.weatherApi;

import Exceptions.CallingApiException;
import API.ApiBase;

public class WeatherApi extends ApiBase {

    private static final String host = "http://api.openweathermap.org/data/2.5/weather?appid=1bf2280610892b23825a629aeb4cddc0&lang=pl&units=metric";
    private static final String city = "&q=";
    private static final String id = "&id=";
    private static final String lat = "&lat=";
    private static final String lon = "&lon=";
    private static final String regex = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$";


    public String callApiWithCityName(String cityName) {
        try {
            return callApi(host + city + fixCityNameIfNecessary(checkIfCityNameIsCorrect(cityName)));
        } catch (Exception e) {
            throw new CallingApiException("Calling OpenWeatherMap API with city name failed", e.getCause());
        }
    }

    public String callApiWithCityId(Integer cityId) {
        try {
            return callApi(host + id + cityId);
        } catch (Exception e) {
            throw new CallingApiException("Calling OpenWeatherMap API with city id failed", e.getCause());
        }
    }

    public String callApiWithLatitudeAndLongitude(double latFromIp, double lonFromIp) {
        try {
            return callApi(host + lat + latFromIp + lon + lonFromIp);
        } catch (Exception e) {
            throw new CallingApiException("Calling OpenWeatherMap API with latitude and longitude failed", e.getCause());
        }
    }

    private String fixCityNameIfNecessary(String cityName) {
            return cityName.replace(" ", "+");
    }

    private String checkIfCityNameIsCorrect(String cityName) {
            if (cityName.matches(regex)) {
                return cityName;
            } else {
                return "";
            }
    }
}
