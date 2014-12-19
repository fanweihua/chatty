/**
 * 
 */
package com.funplus.chatty.event;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class PublicMessage {
    private String error;
    private User sender;
    private String message;
    
    public PublicMessage(String error) {
        this.error = error;
    }
    
    public PublicMessage(User sender, String message) {
        this.sender = sender;
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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
