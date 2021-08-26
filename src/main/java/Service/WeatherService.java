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
    private final List<Integer> alphabeticalMainCitiesIds = new ArrayList<>(List.of(
            776069, 3102014, 3099434, 3098722, 3096472, 769250, 3094802, 765876, 3093133, 763166, 3090048, 3088171,
            759734, 3083829, 3083271, 6695624, 3081368, 3080165));

    private Optional<Integer> searchCityIdInDatabase(String cityName) {
        return cityRepository.findCityIdByCityName(cityName);
    }

    private History convertWeatherDataToHistory(WeatherMaster weatherMaster) {
        return new History(weatherMaster.getCityId(), weatherMaster.getCityName());
    }

    private void saveCityIfStatusCodeIsOk(WeatherMaster weatherMaster) {
        if (weatherMaster.getStatusCode() == 200) {
            historyRepository.saveCityToHistoryOrUpdateIfExists(convertWeatherDataToHistory(weatherMaster));
        }
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
        saveCityIfStatusCodeIsOk(weatherMaster);
        return weatherMaster;
    }

    public WeatherMaster getCurrentWeatherWithIp() {
        WeatherMaster weatherMaster = weatherApi.convertJsonToJava(weatherApi
                .callApiWithLatitudeAndLongitude(ipApi.getLat(), ipApi.getLon()), WeatherMaster.class);
        saveCityIfStatusCodeIsOk(weatherMaster);
        return weatherMaster;
    }

    //metoda nie zapisuje miast do historii, ponieważ jest używana tylko do uzyskania pogody dla miast wojewódzkich
    public WeatherMaster getCurrentWeatherWithCityId(int cityId) {
        return weatherApi.convertJsonToJava(weatherApi.callApiWithCityId(cityId), WeatherMaster.class);
    }

    public List<WeatherMaster> getCurrentWeatherForMainCities() {
        List<WeatherMaster> citiesList = new ArrayList<>();
        for (Integer c : alphabeticalMainCitiesIds) {
            citiesList.add(getCurrentWeatherWithCityId(c));
        }
        return citiesList;
    }
}
