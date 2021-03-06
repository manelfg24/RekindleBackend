
package com.pes.rekindle.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pes.rekindle.entities.Message;

@JsonInclude(Include.NON_NULL)
public class DTOMessage {
    private long id;

    private long idChat;

    private DTOUser owner;

    private String content;

    private Date timestamp;

    public DTOMessage() {
        super();
    }

    public DTOMessage(Message message) {
        super();
        this.id = message.getId();
        this.idChat = message.getChat().getId();
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdChat() {
        return idChat;
    }

    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    public DTOUser getOwner() {
        return owner;
    }

    public void setOwner(DTOUser owner) {
        this.owner = owner;
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

}
