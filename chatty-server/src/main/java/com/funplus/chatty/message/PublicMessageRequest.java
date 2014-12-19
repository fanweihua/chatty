/**
 * 
 */
package com.funplus.chatty.message;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class PublicMessageRequest extends Request {
    private User from;
    private String message;
    
    public PublicMessageRequest(User from, String message) {
        this.from = from;
        this.message = message;
    }
    
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
