/**
 * 
 */
package com.funplus.chatty.entity;

import org.json.JSONException;
import org.json.JSONObject;

import com.funplus.chatty.controller.Session;

/**
 * @author Weihua Fan
 *
 */
public class User {
    private int id;
    private String name;
    private String language;
    private Session session;
    
    public User(int id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static User newFromJsonObject(JSONObject obj) {
        try {
            return new User(obj.getInt("id"), obj.getString("n"), obj.getString("l"));
        } catch (JSONException e) {
            return null;
        }
    }
    
    public JSONObject toJSONObject() {
        JSONObject userObject = new JSONObject();
        try {
            userObject.put("id", id);
            userObject.put("n", name);
            userObject.put("l", language);
        } catch (JSONException e) {
        }
        
        return userObject;
    }
    
}
