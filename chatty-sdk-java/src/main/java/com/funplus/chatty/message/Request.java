/**
 * 
 */
package com.funplus.chatty.message;

import org.json.JSONObject;

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
    
    protected int id;

    public Request(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract JSONObject build();
}
