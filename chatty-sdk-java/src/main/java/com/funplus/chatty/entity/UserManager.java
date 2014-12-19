/**
 * 
 */
package com.funplus.chatty.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weihua Fan
 *
 */
public class UserManager {
    
    private Map<Integer, User> userList;
    private User self;
    
    public UserManager() {
        this.userList = new HashMap<>();
    }
    
    public void addUsers(List<User> users) {
        for (User user : users) {
            userList.put(user.getId(), user);
        }
    }
    
    public void addUser(User user) {
        userList.put(user.getId(), user);
    }
    
    public User getUser(int id) {
        return userList.get(id);
    }
    
    public void removeUser(int id) {
        userList.remove(id);
    }

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }
    
    public List<User> getUserList() {
        return new ArrayList<>(userList.values());
    }
    
}
