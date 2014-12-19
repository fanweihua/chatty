/**
 * 
 */
package com.funplus.chatty.event;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class PrivateMessage {
    private String error;
    private User sender;
    private User recepient;
    private String message;
    
    public PrivateMessage(String error) {
        this.error = error;
    }
    
    public PrivateMessage(User sender, User recepient, String message) {
        this.sender = sender;
        this.recepient = recepient;
        this.message = message;
    }
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getRecepient() {
        return recepient;
    }
    public void setRecepient(User recepient) {
        this.recepient = recepient;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
