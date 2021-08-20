package repository.HistoryRepository;

import lombok.ToString;
import repository.RepositoryBase;

import java.time.LocalDateTime;
import java.util.List;

@ToString
public class HistoryRepository extends RepositoryBase {

    public long getActualSizeOfCityHistory() {
        return entityManager.createQuery("SELECT COUNT(*) FROM History", Long.class)
                .getSingleResult();
    }

    public void deleteOldestHistory() {
        doInTransaction(getHistory().get(0), entityManager::remove);
    }

    public List<History> getHistory() {
        return entityManager.createQuery("FROM History h ORDER BY h.date", History.class)
                .getResultList();
    }


    public void saveCityToHistoryOrUpdateIfExists(History cityToAdd) {
        List<History> history = getHistory();
        for (History h : history) {
            if (h.getCityId() == cityToAdd.getCityId()) {
                doInTransaction(cityToAdd, entry -> entityManager.createQuery("UPDATE History h SET h.date = :newDate WHERE h.cityId = :cityId")
                        .setParameter("newDate", LocalDateTime.now())
                        .setParameter("cityId", cityToAdd.getCityId())
                        .executeUpdate());
                return;
            }
        }
        if (history.size() >= 5) {
            deleteOldestHistory();
        }
        doInTransaction(cityToAdd, entry -> entityManager.persist(cityToAdd));
    }
}

// FIXME - DESC <- najnowsze w historii ||| ASC (lub bez) <- najstarsze w historii
