package movies_db.storage;

import movies_db.movie.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateStorage extends GenericDBStorage implements IStorage {
    private final SessionFactory sessionFactory;
    public HibernateStorage() {
        super();
        String password = getDBPassword();
        Configuration hibernateConfig = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .setProperty("hibernate.connection.password", password);
        sessionFactory = hibernateConfig.buildSessionFactory();
    }

    @Override
    public void add(Movie m) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(m);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Movie> movies = session.createQuery("from Movie", Movie.class).getResultList();
            concatenateResultsAndDisplay(movies);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
