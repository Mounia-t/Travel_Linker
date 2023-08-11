package travelLinker.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import travelLinker.dao.ConversationDao;
import travelLinker.entity.Message;
import travelLinker.utils.SessionUtils;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
@SessionScoped
public class ConversationController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private AccountViewModel accVM=new AccountViewModel();

	 @Inject
	    private ConversationDao conversationDao;

	 public void sendMessage() {
		// String senderEmail =conversationDao.getEmail();
	
		 conversationDao.sendMessage( accVM);	 
		 }
	 


	 public List<Message> getReceivedMessages() {
		    HttpSession session = SessionUtils.getSession();
		    String recipientEmail = (String) session.getAttribute("email");
		    System.out.println(recipientEmail);
		    return conversationDao.getReceivedMessages(recipientEmail);
		}
	    
	 public List<Message>getSentMessages(){
		
			    HttpSession session = SessionUtils.getSession();
			    String senderEmail = (String) session.getAttribute("email");
			    System.out.println(senderEmail);
			    return conversationDao.getSentMessages(senderEmail);
			
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
	 
	 

}
