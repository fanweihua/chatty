/**
 * 
 */
package com.funplus.chatty.message;

import org.json.JSONException;
import org.json.JSONObject;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class PrivateMessageResponse extends Response {
    private User from;
    private User recepient;
    private String message;
    private String error;
    
    public PrivateMessageResponse(User from, User recepient, String message) {
        setId(Request.PrivateMessage);
        this.from = from;
        this.recepient = recepient;
        this.message = message;
    }
    
    public PrivateMessageResponse(String error) {
        setId(Request.PrivateMessage);
        this.error = error;
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

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", getId());
            if (error == null) {
                msg.put("sd", from.getId());
                msg.put("rc", recepient.getId());
                msg.put("m", message);
            } else {
                msg.put("er", error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }
}
