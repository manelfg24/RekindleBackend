
package com.pes.rekindle.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /*
    @NotNull
    private long idChat;
    */
    @NotNull
    private String mailSender;
    @NotNull
    private String content;
    @NotNull
    private Date timestamp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idChat")
    private Chat chat;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
/*
	public long getIdChat() {
		return idChat;
	}

	public void setIdChat(long idChat) {
		this.idChat = idChat;
	}
*/
	public String getMailSender() {
		return mailSender;
	}

	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
}
