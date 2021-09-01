import API.WeatherApi.WeatherProperties.WeatherMaster;
import Service.WeatherService;

public class LauncherConsole {

    public static void main(String[] args) {

        WeatherService service = new WeatherService();

        System.out.println("\nPogoda dla miast wojewódzkich w Polsce (po ID dla szybszych wyników):\n");
        for (WeatherMaster city : service.getCurrentWeatherForMainCities()) {
            System.out.println("\n" + city + "\n");
        }

        System.out.println("\nPogoda uzyskana na podstawie nazwy miasta, które nie zostało odnalezione w bazie:\n");
        System.out.println(service.getCurrentWeatherWithCityName("Wójtostwo"));

        System.out.println("\nPogoda uzyskana na podstawie nazwy miasta, które zostało odnalezione w bazie:\n");
        System.out.println(service.getCurrentWeatherWithCityName("Gdańsk"));

        System.out.println("\nPogoda uzyskana na podstawie ID miasta:\n");
        System.out.println(service.getCurrentWeatherWithCityId(3096472));

        System.out.println("\nPogoda uzyskana na podstawie lokalizacji IP:\n");
        System.out.println(service.getCurrentWeatherWithIp());
    }
}
