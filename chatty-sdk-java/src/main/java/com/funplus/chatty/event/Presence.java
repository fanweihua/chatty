/**
 * 
 */
package com.funplus.chatty.event;

import com.funplus.chatty.entity.User;

/**
 * @author Weihua Fan
 *
 */
public class Presence {

    private User user;
    private boolean online;
    private String error;

    public Presence(User user, boolean online) {
        this.user = user;
        this.online = online;
    }

    public Presence(String error) {
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

}
