/**
 * 
 */
package com.funplus.chatty.message;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class HeartbeatRequest extends Request {
    private User from;
    
    public HeartbeatRequest(User from) {
        this.from = from;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }
    
}
