/**
 * 
 */
package com.funplus.chatty.message;

import org.json.JSONObject;

import com.funplus.chatty.entity.Session;

import java.util.List;

/**
 * @author Weihua Fan
 *
 */
public abstract class Response {

    private int id;
    private List<Session> recipients;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Session> getRecipients() {
        return recipients;
    }
    
    public void setRecipients(List<Session> recipients) {
        this.recipients = recipients;
    }
    
    public abstract JSONObject build();
}
