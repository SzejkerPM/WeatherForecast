package Repository;

import org.apache.commons.lang3.Conversion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

public abstract class RepositoryBase extends Conversion {


    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    protected final EntityManager entityManager = databaseConnection.getEntityManagerFactory().createEntityManager();

    public <T> void doInTransaction(T entity, Consumer<T> consumer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        consumer.accept(entity);
        transaction.commit();
    }
}
