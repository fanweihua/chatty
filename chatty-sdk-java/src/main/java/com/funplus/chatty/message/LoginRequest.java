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
public class LoginRequest extends Request {
    
    private String userName;
    private String password;
    
    public LoginRequest(String userName, String password) {
        super(Request.Login);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", id);
            msg.put("n", userName);
            msg.put("pw", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
