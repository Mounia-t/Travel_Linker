package travelLinker.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import travelLinker.entity.Account;
import travelLinker.entity.Message;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@Stateless
public class ConversationDao {

	@PersistenceContext(unitName = "travelLinker")
	private EntityManager entityManager;
	@Inject
	private LoginDao loginDao;

	public void sendMessage(AccountViewModel accVM) {
		String senderEmail = SessionUtils.getUserEmail();
		Long idSender = SessionUtils.getUserId();
		Account recipient = loginDao.findAccountByEmail(accVM.getRecepientEmail());
		Long idRecipient = recipient.getId();

		if (recipient != null) {

			Message message = new Message();
			message.setRecepientEmail(accVM.getRecepientEmail());
			message.setContent(accVM.getContent());
			message.setMessageResume(accVM.getMessageResume());
			message.setSenderEmail(senderEmail);
			message.setSenderId(idSender);
			message.setRecepientId(idRecipient);
			entityManager.persist(message);
		}
		// System.out.println("Message introuvbale");

	}



	public String getEmail() {
		String EmailSender = SessionUtils.getUserEmail();
		return EmailSender;
	}
	

	public List<Message> getReceivedMessages(String recipientEmail) {
	    TypedQuery<Message> query = entityManager.createQuery(
	        "SELECT m FROM Message m WHERE m.recipientEmail = :recipientEmail", Message.class);
	    query.setParameter("recipientEmail", recipientEmail);
	    return query.getResultList();
	}

	public List<Message> getSentMessages(String senderEmail) {
	    TypedQuery<Message> query = entityManager.createQuery(
	        "SELECT m FROM Message m WHERE m.senderEmail = :senderEmail", Message.class);
	    query.setParameter("senderEmail", senderEmail);
	    return query.getResultList();
	}
}