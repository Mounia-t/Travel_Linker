package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import travelLinker.entity.Account;
import travelLinker.entity.Message;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class ConversationDao {

	@PersistenceContext(unitName = "travelLinker")
	private EntityManager entityManager;
	@Inject
	private LoginDao loginDao;

	public void sendMessage(AccountViewModel accVM, Long senderId, String recipientEmail, String content) {
		Account sender = entityManager.find(Account.class, senderId);
		Account recipient;
		recipient = loginDao.findAccountByEmail(recipientEmail);
		if (recipient != null) {
	
				Message message = new Message(sender, recipient, content);
	            message.setSender(accVM.getSender());
	            message.setContent(accVM.getContent());
	            message.setDest(accVM.getDest());
			entityManager.persist(message);
		} else {
			System.out.println("Message introuvbale");
		}

	}
	 public List<Message> getMessages(int senderId, int recipientId) {
		    String queryString = "SELECT m FROM Message m WHERE (m.sender.id = :senderId AND m.recipient.id = :recipientId) OR (m.sender.id = :recipientId AND m.recipient.id = :senderId)";
		    TypedQuery<Message> query = entityManager.createQuery(queryString, Message.class);
		    query.setParameter("senderId", senderId);
		    query.setParameter("recipientId", recipientId);

		    return query.getResultList();
		}
	
	 

}
