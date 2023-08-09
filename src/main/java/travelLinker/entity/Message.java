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
    
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account dest;
    
    @ManyToOne
    private Conversation conversation;
    
    public Message() {
    }
    
    public Message(Account sender, Account dest , String content) {
        this.sender = sender;
        this.dest = dest;
        this.content = content;
    }
    
    public Message(Account sender, Conversation conv , String content) {
        this.sender = sender;
        this.conversation = conv;
        this.content = content;
    }
    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Account getSender() {
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

	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	
    
}
