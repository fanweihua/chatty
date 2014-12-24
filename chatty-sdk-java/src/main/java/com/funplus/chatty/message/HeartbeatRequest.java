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
public class HeartbeatRequest extends Request {
    
    private User user;

    public HeartbeatRequest(User user) {
        super(Request.Heartbeat);
        this.user = user;
    }

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", id);
            msg.put("u", user.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
