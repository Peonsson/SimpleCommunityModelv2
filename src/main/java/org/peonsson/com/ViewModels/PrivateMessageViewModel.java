package org.peonsson.com.ViewModels;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Peonsson on 27/11/15.
 */
@XmlRootElement
public class PrivateMessageViewModel {

    private String sender;
    private String receiver;
    private String subject;
    private String message;
    private Date timestamp;

    public PrivateMessageViewModel() {
    }

    public PrivateMessageViewModel(String sender, String receiver, String subject, String message, Date timestamp) {

        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.timestamp = timestamp;
    }

    public PrivateMessageViewModel(String sender, String receiver, String subject, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
