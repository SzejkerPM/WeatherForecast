import Service.WeatherService;
import api.weatherApi.jsonToJava.WeatherMaster;

import java.util.List;


public class Launcher {

    public static void main(String[] args) {

        // T E S T O W A N K O


        //FUNKCJONALNOŚCI 100%

        //REFACTORING 90%

        //TESTY 0%

        //GUI 1%

        //REFACTORING GUI 0%


        // Service zawiera wszystkie gotowe metody do użycia
        WeatherService weatherService = new WeatherService();

        System.out.println("Pogoda dla miast wojewódzkich:");
        List<WeatherMaster> cities = weatherService.getCurrentWeatherForMainCities();

        for (WeatherMaster c : cities) {
            System.out.println();
            System.out.println(c);
            System.out.println();
        }

    }
}
