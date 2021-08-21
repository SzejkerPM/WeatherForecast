package BuildCityDatabase;

import Exceptions.ConversionFromJsonException;
import Repository.CityRepository.City;
import Repository.DatabaseConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BuildDatabase{

    private static final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private static final EntityManager entityManager = databaseConnection.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) {

        String filePath = "src/main/resources/city.list.json";
        List<CityObject> cityObjects = convertJsonToObject(filePath);

        insertObjectsIntoDatabase(cityObjects);
    }

    private static List<CityObject> convertJsonToObject(String filePath) {
        try {
            return tryToConvertJsonToObject(filePath);
        } catch (Exception e) {
            throw new ConversionFromJsonException("Conversion from Json file failed", e.getCause());
        }
    }

    private static List<CityObject> tryToConvertJsonToObject(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(new File(filePath), new TypeReference<>() {});
    }

    private static void insertObjectsIntoDatabase(List<CityObject> cities) {
        fillDatabaseWithData(cities.stream()
                .map(x -> new City(x.getCityId(), x.getCityName())).collect(Collectors.toList()));
    }

    private static void fillDatabaseWithData(List<City> cities) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        cities.forEach(entityManager::persist);
        transaction.commit();
    }
}
