//TUTORIAL SKŁADNI HQL
//https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm
//(posługujemy się nazwami klas/pól, a nie kolumn tabeli!)

//update'y bazy: save() lub samo get() + modyfikacja obiektu przed committem

package hibernate_examples;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure()
                .addAnnotatedClass(Game.class);
//        configuration.configure("src/main/resources/hibernate.cfg.xml");
        //SessionFactory robimy raz na aplikację (ciężki obiekt)
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
//            Game g1 = new Game("Save_Test2", 250.0, "outside transaction");
//            session.save(g1);
//            populateWithTestGames(session);
//            readAndDisplayGames(session);
//            updateTestGame(session);
           // deleteTestGame(session);
            readMany(session);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void readAndDisplayGames(Session session) {
        Transaction transaction = session.beginTransaction();
        Game gameWithID3 = session.get(Game.class, 3); //drugim parametrem jest ID
        Game gameWithIDNotExisting = session.get(Game.class, 3000000); //drugim parametrem jest ID
        //Game gameWithIDNotExistingException = session.load(Game.class, 3000000); //drugim parametrem jest ID
        System.out.println(gameWithID3);
        System.out.println(gameWithIDNotExisting);
        //zmiany w pamięci jeszcze są śledzone
        // w tej samej tranzakcji!
        gameWithID3.setPlatform("zmiana");
        gameWithID3.setTitle("zmiana");
        transaction.commit();
    }

    private static void updateTestGame(Session session) {
        Transaction transaction = session.beginTransaction();
        Game g = session.find(Game.class, 1);
        g.setTitle("Different update");
        Game up = (Game) session.merge(g);
        transaction.commit();
    }

    private static void deleteTestGame(Session session) {
        Transaction transaction = session.beginTransaction();
        Game g = session.find(Game.class, 6);
        session.delete(g);
        transaction.commit();
    }

    private static void readMany(Session session) {
        Transaction transaction = session.beginTransaction();
        List<Game> games = session.createQuery("from Game G where G.id < 10", Game.class).getResultList();
        System.out.println(games);
        transaction.commit();
    }

    private static void populateWithTestGames(Session session) {
        Transaction transaction = session.beginTransaction();
        for (int i = 0; i < 10; i++) {
            Game g = getGame();
            session.persist(g);
        }
        transaction.commit();
    }

    private static Game getGame() {
        Random rng = new Random();
        return new Game("Hibernate-Test", rng.nextDouble(10, 100), "Hibernate");
    }
}

//try (Session session = sessionFactory.openSession()) {
//        Transaction transaction = session.beginTransaction();
//
//        // wykonanie operacji bazodanowych
//
//        if (success) {
//        transaction.commit();
//        } else {
//        transaction.rollback();
//        }
//        }