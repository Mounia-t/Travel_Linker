package travelLinker.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
	private static EntityManagerFactory entityManagerFactory;
	private HibernateUtils() {
	}
 static {
        entityManagerFactory = Persistence.createEntityManagerFactory("travelLinker");
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
    

}






