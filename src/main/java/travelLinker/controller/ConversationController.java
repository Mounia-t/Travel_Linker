package travelLinker.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import travelLinker.dao.ConversationDao;
import travelLinker.entity.Message;
import travelLinker.viewModel.AccountViewModel;

@ManagedBean
public class ConversationController {
	private AccountViewModel accVM=new AccountViewModel();

	 @Inject
	    private ConversationDao conversationDao;

	 public void sendMessage(Long senderId, String recipientEmail, String content) {
		 conversationDao.sendMessage( accVM, senderId, recipientEmail, content);	 
		 }
	 
	 public List<Message> getMessages(int senderId, int recipientId){
		 return conversationDao.getMessages(senderId, recipientId);
	 }
	 

}
