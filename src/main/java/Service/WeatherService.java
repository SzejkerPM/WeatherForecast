package Service;

import API.ipApi.IpApi;
import API.weatherApi.WeatherApi;
import API.weatherApi.jsonToJava.WeatherMaster;
import Repository.CityRepository.CityRepository;
import Repository.HistoryRepository.History;
import Repository.HistoryRepository.HistoryRepository;

import java.util.*;

public class WeatherService {

    private final WeatherApi weatherApi = new WeatherApi();
    private final IpApi ipApi = new IpApi();
    private final CityRepository cityRepository = new CityRepository();
    private final HistoryRepository historyRepository = new HistoryRepository();
    private final Map<String, Integer> mainCities = Map.ofEntries(
            Map.entry("Białystok", 776069),
            Map.entry("Bydgoszcz", 3102014),
            Map.entry("Gdańsk", 3099434),
            Map.entry("Gorzów Wielkopolski", 3098722),
            Map.entry("Katowice", 3096472),
            Map.entry("Kielce", 769250),
            Map.entry("Kraków", 3094802),
            Map.entry("Lublin", 765876),
            Map.entry("Łódź", 3093133),
            Map.entry("Olsztyn", 763166),
            Map.entry("Opole", 3090048),
            Map.entry("Poznań", 3088171),
            Map.entry("Rzeszów", 759734),
            Map.entry("Szczecin", 3083829),
            Map.entry("Toruń", 3083271),
            Map.entry("Warszawa", 6695624),
            Map.entry("Wrocław", 3081368),
            Map.entry("Zielona Góra", 3080165));

    private Optional<Integer> searchCityIdInDatabase(String cityName) {
        return cityRepository.findCityIdByCityName(cityName);
    }

    private History convertWeatherDataToHistory(WeatherMaster weatherMaster) {
        return new History(weatherMaster.getCityId(), weatherMaster.getCityName());
    }

    //metoda dodatkowo przeszukuje bazę w poszukiwaniu ID dla lepszych wyników
    public WeatherMaster getCurrentWeatherWithCityName(String cityName) {
        Optional<Integer> cityIdFromCityName = searchCityIdInDatabase(cityName);
        WeatherMaster weatherMaster;
        if (cityIdFromCityName.isPresent()) {
            weatherMaster = weatherApi
                    .convertJsonToJava(weatherApi.callApiWithCityId(cityIdFromCityName.get()), WeatherMaster.class);
        } else {
            weatherMaster = weatherApi
                    .convertJsonToJava(weatherApi.callApiWithCityName(cityName), WeatherMaster.class);
        }
        if (weatherMaster.getCityName() != null) {
            historyRepository.saveCityToHistoryOrUpdateIfExists(convertWeatherDataToHistory(weatherMaster));
        }
        return weatherMaster;
    }

    public WeatherMaster getCurrentWeatherWithIp() {
        WeatherMaster weatherMaster = weatherApi.convertJsonToJava(weatherApi
                .callApiWithLatitudeAndLongitude(ipApi.getLat(), ipApi.getLon()), WeatherMaster.class);
        historyRepository.saveCityToHistoryOrUpdateIfExists(convertWeatherDataToHistory(weatherMaster));
        return weatherMaster;
    }

    //metoda nie zapisuje miast do historii, ponieważ jest używana tylko do uzyskania pogody dla miast wojewódzkich
    public WeatherMaster getCurrentWeatherWithCityId(int cityId) {
        return weatherApi.convertJsonToJava(weatherApi.callApiWithCityId(cityId), WeatherMaster.class);
    }

    public List<WeatherMaster> getCurrentWeatherForMainCities() {
        List<WeatherMaster> citiesList = new ArrayList<>();
        for (Integer c : mainCities.values()) {
            citiesList.add(getCurrentWeatherWithCityId(c));
        }
        return citiesList;
    }
}
