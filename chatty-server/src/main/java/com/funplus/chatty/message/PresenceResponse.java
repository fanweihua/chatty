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
public class PresenceResponse extends Response {

    private User user;
    private boolean online;
    private String error;

    public PresenceResponse(User user, boolean online) {
        setId(Request.Presence);
        this.user = user;
        this.online = online;
    }

    public PresenceResponse(String error) {
        setId(Request.Presence);
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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
                msg.put("u", user.toJSONObject());
                msg.put("p", online);
            } else {
                msg.put("er", error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
