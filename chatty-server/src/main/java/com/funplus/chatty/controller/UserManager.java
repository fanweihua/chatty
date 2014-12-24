/**
 * 
 */
package com.funplus.chatty.controller;

import com.funplus.chatty.entity.Session;
import com.funplus.chatty.entity.User;
import com.funplus.chatty.message.LoginRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Weihua Fan
 *
 */
public class UserManager {

    private AtomicInteger idGenerator = new AtomicInteger(0);
    //private Map<String, User> usersByName;
    private Map<Session, User> usersBySession;
    private Map<Integer, User> usersById;
    
    public UserManager() {
        this.usersBySession = new ConcurrentHashMap<>();
        //this.usersByName = new ConcurrentHashMap<>();
        this.usersById = new ConcurrentHashMap<>();
    }
    
    public void addUser(User user) {
        this.usersById.put(user.getId(), user);
        //this.usersByName.put(user.getName(), user);
        this.usersBySession.put(user.getSession(), user);
    }

    public User getUserById(int id) {
        return usersById.get(id);
    }

//    public User getUserByName(String name) {
//        return usersByName.get(name);
//    }

    public User getUserBySession(Session session) {
        return usersBySession.get(session);
    }
    
    public User createUser(LoginRequest login) {
        User user = new User(idGenerator.incrementAndGet(), login.getName(), "en");
        user.setSession(login.getSender());
        return user;
    }
    
    public List<User> getUserList() {
        return new ArrayList<>(usersById.values());
    }
    
    public void removeUser(Session session) {
        User user = usersBySession.get(session);
        if (user != null) {
            removeUser(user);
        }
    }
    
    public void removeUser(User user) {
      usersById.remove(user.getId());
//      usersByName.remove(user.getName());
      usersBySession.remove(user.getSession());
    }
    
    public boolean containUser(User user) {
        return usersById.containsKey(user.getId());
    }
    
}
