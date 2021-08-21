package Repository.HistoryRepository;

import lombok.ToString;
import Repository.RepositoryBase;

import java.time.LocalDateTime;
import java.util.List;

@ToString
public class HistoryRepository extends RepositoryBase {

    // ASC w nazwie odnosi się do sortowania (ASC = od najstarszych wyników)
    private List<History> getHistoryAsc() {
        return entityManager.createQuery("FROM History h ORDER BY h.date", History.class)
                .getResultList();
    }

    // DESC = od najnowszych wyników
    public List<History> getHistoryDesc() {
        return entityManager.createQuery("FROM History h ORDER BY h.date DESC", History.class)
                .getResultList();
    }

    private void deleteOldestHistory() {
        doInTransaction(getHistoryAsc().get(0), entityManager::remove);
    }

    public void saveCityToHistoryOrUpdateIfExists(History cityToAdd) {
        List<History> history = getHistoryAsc();
        for (History h : history) {
            if (h.getCityId() == cityToAdd.getCityId()) {
                doInTransaction(cityToAdd, entry -> entityManager
                        .createQuery("UPDATE History h SET h.date = :newDate WHERE h.cityId = :cityId")
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

