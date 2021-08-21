package Repository.CityRepository;


import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryTest {

    CityRepository cityRepository = new CityRepository();

    @Test
    void shouldFindCityIdWithCityName() {
        String cityName = "Katowice";

        Optional<Integer> result = cityRepository.findCityIdByCityName(cityName);

        assertEquals(3096472, result.get());
    }

    @Test
    void shouldNotFindAnyIdWithWrongCityName() {
        String wrongCityName = "whops";

        Optional<Integer> result = cityRepository.findCityIdByCityName(wrongCityName);

        assertEquals(Optional.empty(), result);
    }

}