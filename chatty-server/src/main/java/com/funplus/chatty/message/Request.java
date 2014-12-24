/**
 * 
 */
package com.funplus.chatty.message;

import com.funplus.chatty.entity.Session;

/**
 * @author Weihua Fan
 *
 */
public abstract class Request {
    
    public static final int Login = 1;
    public static final int Logout = 2;
    public static final int PublicMessage = 3;
    public static final int PrivateMessage = 4;
    public static final int Heartbeat = 5;
    public static final int Presence = 6;
    
    private int id;
    private Session sender;
    private long timeStamp;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Session getSender() {
        return sender;
    }
    
    public void setSender(Session sender) {
        this.sender = sender;
    }
    
    public long getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
}
