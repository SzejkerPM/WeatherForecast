package Service;

import api.ipApi.IpApi;
import api.weatherApi.WeatherApi;
import api.weatherApi.jsonToJava.WeatherMaster;
import repository.CityRepository.CityRepository;
import repository.DatabaseConnection;
import repository.HistoryRepository.History;
import repository.HistoryRepository.HistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeatherService {

    //FIXME souty w ramach testów

    private final WeatherApi weatherApi = new WeatherApi();
    private final IpApi ipApi = new IpApi();
    private final CityRepository cityRepository = new CityRepository();
    private final HistoryRepository historyRepository = new HistoryRepository();
    private final List<String> mainCities = List.of("Białystok", "Bydgoszcz", "Gdańsk", "Gorzów+Wielkopolski", "Katowice",
            "Kielce", "Kraków", "Lublin", "Łódź", "Olsztyn", "Opole", "Poznań", "Rzeszów", "Szczecin", "Toruń",
            "Warszawa", "Wrocław", "Zielona+Góra");

    /*private HashMap<String, Integer> mainCities = new HashMap<>();

    private HashMap<String, Integer> fillMapWithData() {
        mainCities.put("Białystok", 432423)
    }*/

    private Optional<Integer> searchCityIdInDatabase(String cityName) {
        return cityRepository.findCityIdByCityName(cityName);
    }

    private History convertWeatherDataToHistory(WeatherMaster weatherMaster) {
        return new History(weatherMaster.getCityId(), weatherMaster.getCityName());
    }

    /*private void addHistoryToDatabase(History history) {
        historyRepository.saveCityToHistory(history);
    }*/

    //TODO sprawdzić czy takie zapisywanie historii działa

    public WeatherMaster getCurrentWeatherWithCityName(String cityName) {
        Optional<Integer> cityIdFromCityName = searchCityIdInDatabase(cityName);
        if (cityIdFromCityName.isPresent()) {
            System.out.println("\nPogoda wyświetlona na podstawie id miasta (znaleziono w bazie)");
            WeatherMaster weatherMaster = weatherApi.convertJsonToJava(weatherApi.callApiWithCityId(cityIdFromCityName.get()), WeatherMaster.class);
            historyRepository.saveCityToHistoryOrUpdateIfExists(convertWeatherDataToHistory(weatherMaster));
            return weatherMaster;
        } else {
            System.out.println("\nPogoda wyświetlona na podstawie nazwy miasta (brak danych w bazie)");
            WeatherMaster weatherMaster = weatherApi.convertJsonToJava(weatherApi.callApiWithCityName(cityName), WeatherMaster.class);
            historyRepository.saveCityToHistoryOrUpdateIfExists(convertWeatherDataToHistory(weatherMaster));
            return weatherMaster;
        }
    }

    public WeatherMaster getCurrentWeatherWithIp() {
        System.out.println("Pogoda wyświetlona przy użyciu lokalizacji IP");
        return weatherApi.convertJsonToJava(weatherApi.
                callApiWithLatitudeAndLongitude(ipApi.getLat(), ipApi.getLon()), WeatherMaster.class);
    }

    public List<WeatherMaster> getCurrentWeatherForMainCities() {
        List<WeatherMaster> citiesList = new ArrayList<>();
        for (String s : mainCities) {
            citiesList.add(getCurrentWeatherWithCityName(s));
        }
        return citiesList;
    }

    // Po co dawać varargs skoro mamy stałą ilość miast?
    // Po co robić warunek na zmianę spacji, skoro mogę dodać do tablicy gotowe nazwy? (gdzie je umieścić optymalnie?)
}
