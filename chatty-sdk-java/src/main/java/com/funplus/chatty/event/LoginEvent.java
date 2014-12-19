/**
 * 
 */
package com.funplus.chatty.event;

import com.funplus.chatty.entity.User;

import java.util.List;

/**
 * @author Weihua Fan
 *
 */
public class LoginEvent {
    private User self;
    private List<User> userList;
    private String error;
    
    public LoginEvent(User self, List<User> userList) {
        this.self = self;
        this.userList = userList;
    }
    
    public LoginEvent(String error) {
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
    
}
