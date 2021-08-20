package repository;

import api.weatherApi.CallingApiException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import repository.CityRepository.City;
import repository.CityRepository.CityRepository;
import repository.jsonToJava.CityObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FillDatabase extends RepositoryBase {

    // FIXME ogarnąć tutaj refactor

    private String filePath = "src/main/resources/city.list.json";
    /*private CityRepository repository = createLocationRepository(DatabaseConnection.getInstance());*/
    private CityRepository repository = new CityRepository();
    private List<CityObject> objects = convertJsonToObject(filePath);
    //insertObjectsIntoDatabase(objects, repository); do wywołania w main


   /* private static CityRepository createLocationRepository(DatabaseConnection database) {
        return new CityRepository(database);
    }*/

    private static List<CityObject> convertJsonToObject(String filePath) {
        try {
            return tryToConvertJsonToObject(filePath);
        } catch (IOException e) {
            throw new CallingApiException("error", e.getCause()); //FIXME: zrobić nowy wyjątek
        }
    }

    private static List<CityObject> tryToConvertJsonToObject(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(new File(filePath), new TypeReference<>() {
        });

    }

    private static void insertObjectsIntoDatabase(List<CityObject> cities, CityRepository repository) {
        repository.fillDatabaseWithData(cities.stream()
                .map(x -> new City(x.getCityId(), x.getCityName())).collect(Collectors.toList()));
    }
}
