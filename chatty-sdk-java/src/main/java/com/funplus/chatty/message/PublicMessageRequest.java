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
public class PublicMessageRequest extends Request {

    private User from;
    private String message;
    
    public PublicMessageRequest(User from, String message) {
        super(Request.PublicMessage);
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

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", id);
            msg.put("sd", from.getId());
            msg.put("m", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
