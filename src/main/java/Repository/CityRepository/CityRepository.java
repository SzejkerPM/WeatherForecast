package Repository.CityRepository;

import Repository.RepositoryBase;

import java.util.Optional;

public class CityRepository extends RepositoryBase {

    public Optional<Integer> findCityIdByCityName(String cityName) {
        return entityManager.createQuery("SELECT cityId FROM City c WHERE cityName =:cityName", Integer.class)
                .setParameter("cityName", cityName)
                .getResultList().stream().findFirst();
    }
}
