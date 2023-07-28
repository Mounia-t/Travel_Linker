package travelLinker.main;

import javax.persistence.EntityManager;

import travelLinker.utils.HibernateUtils;

public class Main {
	
	public static void main(String[] args) {
		
	
	try {
        EntityManager em = HibernateUtils.createEntityManager();

        // On ferme l'entity manager 
        // pour nettoyer les ressources
        em.close();

        // on ferme aussi la fabrique 
        // d'entity manager
        HibernateUtils.closeEntityManagerFactory();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // On arrête l'application 
        // (à ne pas faire dans une vraie application)
        System.exit(0);
    }}

}
