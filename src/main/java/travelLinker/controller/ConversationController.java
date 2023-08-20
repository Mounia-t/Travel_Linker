package travelLinker.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import travelLinker.dao.ConversationDao;
import travelLinker.entity.Message;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean

public class ConversationController implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountViewModel accVM = new AccountViewModel();
	private Message selectedMessage;
	private Long selectedMessageId;

	@Inject
	private ConversationDao conversationDao;

	public void sendMessage() {
		conversationDao.sendMessage(accVM);
	}

	public List<Message> getReceivedMessages() {
		HttpSession session = SessionUtils.getSession();
		String recipientEmail = (String) session.getAttribute("email");
		System.out.println(recipientEmail);
		return conversationDao.getReceivedMessages(recipientEmail);
	}

	public List<Message> getSentMessages() {

		HttpSession session = SessionUtils.getSession();
		String senderEmail = (String) session.getAttribute("email");
		System.out.println(senderEmail);
		return conversationDao.getSentMessages(senderEmail);
	}

	public List<Message> displayMessages() {
		HttpSession session = SessionUtils.getSession();
		String senderEmail = (String) session.getAttribute("email");
		String recipientEmail = (String) session.getAttribute("recipientEmail"); 
	

		return conversationDao.getAllMessages(senderEmail, recipientEmail);
	}

	  public void markMessageAsRead(Long messageId) {
	      
	        conversationDao.markMessageAsRead(messageId);
	    }
	 
	public void deleteMessage(Long id) {

		conversationDao.deleteMessage(id);
		System.out.println("Message deleted with id : " + id);
	}

	public AccountViewModel getAccVM() {
		return accVM;
	}

	public void setAccVM(AccountViewModel accVM) {
		this.accVM = accVM;
	}

	public ConversationDao getConversationDao() {
		return conversationDao;
	}

	public void setConversationDao(ConversationDao conversationDao) {
		this.conversationDao = conversationDao;
	}

	public Message getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(Message selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public Long getSelectedMessageId() {
		return selectedMessageId;
	}

	public void setSelectedMessageId(Long selectedMessageId) {
		this.selectedMessageId = selectedMessageId;
	}
	public void selectMessage(Message message) {
	    selectedMessage = clearSelectedMessage();
	    selectedMessage = message;
	    System.out.println(selectedMessage);
	}


	 public Message clearSelectedMessage() {
		   Message selectedMessage = new Message();
		return selectedMessage;
		}
}
