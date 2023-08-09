package travelLinker.controller;

import javax.inject.Inject;

import travelLinker.dao.ConversationDao;
import travelLinker.entity.Account;
import travelLinker.entity.Conversation;

public class ConversationController {

	 @Inject
	    private ConversationDao conversationDao;

	    // Method to send a message
	    public void sendMessage(Account sender, Conversation conv, String messageContent) {
	        conversationDao.sendMessage(sender, conv, messageContent);
}
}