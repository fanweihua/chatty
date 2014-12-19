/**
 * 
 */
package com.funplus.chatty.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Weihua Fan
 *
 */
public class LogoutRequest extends Request {
    
    private int id;
    
    public LogoutRequest(int id) {
        super(Request.Logout);
        this.id = id;
    }

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", id);
            msg.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
