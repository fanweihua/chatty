/**
 * 
 */
package com.funplus.chatty.message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.funplus.chatty.entity.User;

import java.util.List;

/**
 * @author Weihua Fan
 *
 */
public class LoginResponse extends Response {
    private String error;
    private User self;
    private List<User> userList;
    
    public LoginResponse(User self, List<User> userList) {
        setId(Request.Login);
        this.self = self;
        this.userList = userList;
    }
    
    public LoginResponse(String error) {
        setId(Request.Login);
        this.error = error;
    }
    
    public User getSelf() {
        return self;
    }
    public void setSelf(User self) {
        this.self = self;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public JSONObject build() {
        JSONObject msg = new JSONObject();
        try {
            msg.put("a", getId());
            if (error == null) {
                msg.put("u", self.toJSONObject());
                JSONArray userArray = new JSONArray();
                for (int i=0; i<userList.size();i++) {
                    userArray.put(i, userList.get(i).toJSONObject());
                }
                msg.put("ul", userArray);
            } else {
                msg.put("er", error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }
    
    
}
