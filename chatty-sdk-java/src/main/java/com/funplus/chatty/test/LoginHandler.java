/**
 * 
 */
package com.funplus.chatty.test;

import com.funplus.chatty.ChatClient;
import com.funplus.chatty.event.ConnectEvent;
import com.funplus.chatty.event.LoginEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author Weihua Fan
 *
 */
public class LoginHandler {
    
    private ChatClient client;
    
    public LoginHandler(ChatClient client) {
        this.client = client;
    }
    
//    @Subscribe
//    public void handleConnect(ConnectEvent event) {
//        if (event.getError() != null) {
//            System.out.println(event.getError());
//        } else {
//            System.out.println("Connected to Server.");
//            client.login("user1", "");
//        }
//    }

    @Subscribe
    private void handleLogin(LoginEvent event) {
        if (event.getError() != null) {
            System.out.println(event.getError());
        } else {
            System.out.println(event.getSelf() + "logged in.");
        }
    }
}
