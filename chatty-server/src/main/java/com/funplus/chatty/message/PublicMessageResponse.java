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
public class PublicMessageResponse extends Response {
    private User from;
    private String message;
    private String error;
    
    public PublicMessageResponse(User from, String message) {
        setId(Request.PublicMessage);
        this.from = from;
        this.message = message;
    }
    
    public PublicMessageResponse(String error) {
        setId(Request.PublicMessage);
        this.error = error;
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

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", getId());
            if (error == null) {
                msg.put("sd", from.getId());
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
