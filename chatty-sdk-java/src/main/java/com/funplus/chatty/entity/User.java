/**
 * 
 */
package com.funplus.chatty.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Weihua Fan
 *
 */
public class User {
    private int id;
    private String name;
    private String language;
    private long lastActivityTime;
    
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
    
    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public static User newFromJsonObject(JSONObject obj) {
        try {
            return new User(obj.getInt("id"), obj.getString("n"), obj.getString("l"));
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
    
    
    
}
