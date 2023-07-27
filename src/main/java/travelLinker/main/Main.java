package travelLinker.main;

import javax.persistence.EntityManager;

import travelLinker.utils.HibernateUtils;

public class Main {
	
	public static void main(String[] args) {
		
	
	try {
        System.out.println("Lancement de l'application et démarrage de l'entity manager ...");

        EntityManager em = HibernateUtils.createEntityManager();

        System.out.println("Fermeture des ressources ...");

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
        System.out.println("Fermeture de l'application ...");
        System.exit(0);
    }}

}
