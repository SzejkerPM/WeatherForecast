import Service.WeatherService;
import Repository.HistoryRepository.HistoryRepository;


public class Launcher {

    public static void main(String[] args) {

        // T E S T O W A N K O


        //FUNKCJONALNOŚCI - 100%

        //REFACTORING 70%

        //TESTY 0%

        //GUI 1%

        //TODO sprawdzić wyjątki i ew wymienić

        WeatherService weatherService = new WeatherService();

        HistoryRepository historyRepository = new HistoryRepository();

        System.out.println("Ilość miast w historii: " + historyRepository.getActualSizeOfCityHistory());

        //System.out.println(weatherService.getCurrentWeatherWithCityName("Sulejów"));

        System.out.println(weatherService.getCurrentWeatherWithCityName("Wójtostwo"));

        //System.out.println(weatherService.getCurrentWeatherWithCityName("Kraków"));

        //System.out.println(weatherService.getCurrentWeatherWithCityName("Warszawa"));

        //System.out.println(weatherService.getCurrentWeatherWithCityName("Piotrków Trybunalski"));

        System.out.println();
        System.out.println(weatherService.getCurrentWeatherWithCityName("Gdańsk"));
        System.out.println();
        //System.out.println("Ilość miast w historii: " + historyRepository.getActualSizeOfCityHistory());



        //System.out.println(weatherService.getCurrentWeatherWithIp());

        // System.out.println("Pogoda dla miast wojewódzkich:");

        //List<WeatherMaster> currentWeatherForSeveralCities = weatherService.getCurrentWeatherForMainCities();

        /*for (WeatherMaster currentWeatherForSeveralCity : currentWeatherForSeveralCities) {
            System.out.println();
            System.out.println(currentWeatherForSeveralCity);
            System.out.println();
        }*/

    }
}
