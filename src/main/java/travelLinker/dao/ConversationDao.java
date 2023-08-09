package travelLinker.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import travelLinker.entity.Account;
import travelLinker.entity.Conversation;
import travelLinker.entity.Message;

@Stateless
public class ConversationDao {

	@PersistenceContext(unitName = "travelLinker")
    private EntityManager entityManager;
	
	/*public void sendMessage(int idSrc, int idDest, String text) {
		Account sender = entityManager.find(Account.class, idSrc);
		Account dest = entityManager.find(Account.class, idDest);
		Message m = new Message(sender, dest, text);
		entityManager.persist(m);
}*/

	    public void sendMessage(Account sender, Conversation conversation, String text) {
	        Message m = new Message(sender, conversation, text);
	        entityManager.persist(m);
	    }
		
	}
