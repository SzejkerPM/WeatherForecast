package repository.HistoryRepository;

import lombok.ToString;
import repository.DatabaseConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@ToString
public class HistoryRepository {

    private final EntityManager entityManager;

    public HistoryRepository(DatabaseConnection databaseConnection) {
        this.entityManager = databaseConnection.getEntityManagerFactory().createEntityManager();
    }

    private void doInTransaction(History cityToAdd, Consumer<History> consumer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        consumer.accept(cityToAdd);
        transaction.commit();
    }

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

    //FIXME TESTOWANIE (działa, ale nieładnie wygląda z drugą transakcją)

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
