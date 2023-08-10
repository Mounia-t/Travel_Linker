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

	public List<Message> getMessages(String senderEmail, String recipientEmail) {
	    String queryString = "SELECT m FROM Message m WHERE (m.senderEmail = :senderEmail AND m.recepientEmail = :recipientEmail) OR (m.senderEmail = :recipientEmail AND m.recepientEmail = :senderEmail)";
	    TypedQuery<Message> query = entityManager.createQuery(queryString, Message.class);
	    query.setParameter("senderEmail", senderEmail);
	    query.setParameter("recipientEmail",recipientEmail);

	    return query.getResultList();
	}



	public String getEmail() {
		String EmailSender = SessionUtils.getUserEmail();
		return EmailSender;
	}
	
	public String getRecipientEmailBySenderEmail(String senderEmail) {
	    TypedQuery<String> query = entityManager.createQuery(
	        "SELECT m.recepientEmail FROM Message m WHERE m.senderEmail = :senderEmail", String.class);
	    query.setParameter("senderEmail", senderEmail);
	    
	    List<String> recipientEmails = query.getResultList();
	    if (!recipientEmails.isEmpty()) {
	        return recipientEmails.get(0); // Récupère la première adresse e-mail trouvée
	    }
	    
	    return null; // Aucun destinataire trouvé avec cet e-mail d'expéditeur
	}
}
