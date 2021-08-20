package repository.CityRepository;

import repository.RepositoryBase;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class CityRepository extends RepositoryBase {

    public Optional<Integer> findCityIdByCityName(String cityName) {
        return entityManager.createQuery("SELECT cityId FROM City c WHERE cityName =:cityName", Integer.class)
                .setParameter("cityName", cityName)
                .getResultList().stream().findFirst();
    }

    public void fillDatabaseWithData(List<City> cities) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        cities.forEach(entityManager::persist);
        transaction.commit();
    }
    //FIXME przerobić na użycie RepositoryBase
}
