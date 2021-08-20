package repository.CityRepository;

import repository.DatabaseConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CityRepository {

    private final EntityManager entityManager;

    public CityRepository(DatabaseConnection databaseConnection) {
        this.entityManager = databaseConnection.getEntityManagerFactory().createEntityManager();
    }

    public Optional<Integer> findCityIdByCityName(String cityName) {
        return entityManager.createQuery("SELECT cityId FROM City c WHERE cityName =:cityName", Integer.class)
                .setParameter("cityName", cityName)
                .getResultList().stream().findFirst();
    }

    public void doInTransaction(City city, Consumer<City> consumer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        consumer.accept(city);
        transaction.commit();
    }
//FIXME
    public void fillDatabaseWithData(List<City> cities) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        cities.forEach(entityManager::persist);
        transaction.commit();
    }
}
