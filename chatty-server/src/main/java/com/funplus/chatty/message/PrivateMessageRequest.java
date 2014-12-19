/**
 * 
 */
package com.funplus.chatty.message;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class PrivateMessageRequest extends Request {
    private User from;
    private User recepient;
    private String message;
    
    public PrivateMessageRequest(User from, User recepient, String message) {
        this.from = from;
        this.recepient = recepient;
        this.message = message;
    }
    
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
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
