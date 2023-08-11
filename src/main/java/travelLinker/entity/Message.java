package travelLinker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    


    private String recipientEmail;
    private String senderEmail;
    private Long senderId;
    private Long recepientId;
    private String messageResume;
 
    
    public Message() {
    }
    
    public Message(Account sender, Account dest , String content) {
       // this.sender = sender;
       // this.dest = dest;
        this.content = content;
    }
    

    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	

/*	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getDest() {
		return dest;
	}

	public void setDest(Account dest) {
		this.dest = dest;
	}
*/


	public String getRecepientEmail() {
		return recipientEmail;
	}

	public void setRecepientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(Long recepientId) {
		this.recepientId = recepientId;
	}

	public String getMessageResume() {
		return messageResume;
	}

	public void setMessageResume(String messageResume) {
	    this.messageResume = messageResume;
	}

	
    
}
