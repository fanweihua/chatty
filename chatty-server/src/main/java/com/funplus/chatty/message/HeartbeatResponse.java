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
public class HeartbeatResponse extends Response {
    
    private String error;
    
    public HeartbeatResponse(String error) {
        setId(Request.Heartbeat);
        this.error = error;
    }

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", getId());
            if (error == null) {
                // empty body
            } else {
                msg.put("er", error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
