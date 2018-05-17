
package com.pes.rekindle.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String mailUser1;
    @NotNull
    private String mailUser2;
    
    @OneToMany(
		mappedBy = "chat", 
		cascade = CascadeType.ALL, 
		orphanRemoval = true
    )
    private Set<Message> messages;    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMailUser1() {
		return mailUser1;
	}
	public void setMailUser1(String mailUser1) {
		this.mailUser1 = mailUser1;
	}
	public String getMailUser2() {
		return mailUser2;
	}
	public void setMailUser2(String mailUser2) {
		this.mailUser2 = mailUser2;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
    public void addMessage(Message message) {
        messages.add(message);
        message.setChat(this);
    }
 
    public void removeMessage(Message message) {
        messages.remove(message);
        message.setChat(null);
    }	
}
