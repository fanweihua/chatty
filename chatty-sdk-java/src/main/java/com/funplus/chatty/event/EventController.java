/**
 * 
 */
package com.funplus.chatty.event;

import com.funplus.chatty.ChatClient;
import com.google.common.eventbus.Subscribe;

/**
 * @author Weihua Fan
 *
 */
public class EventController {

    private ChatClient client;

    public EventController(ChatClient client) {
        this.client = client;
    }

//    @Subscribe
//    public void handleConnect(ConnectEvent event) {
//        if (event.getError() != null) {
//            System.out.println(event.getError());
//        } else {
//
//        }
//    }

    @Subscribe
    public void handleLogin(LoginEvent event) {
        if (event.getError() != null) {
            System.out.println(event.getError());
        } else {
            client.getUserManager().addUsers(event.getUserList());
            client.getUserManager().addUser(event.getSelf());
            client.getUserManager().setSelf(event.getSelf());
            System.out.println(event.getSelf().getId()+":"+event.getSelf().getName() + " logged in.");
        }
    }

    @Subscribe
    public void handlePrivateMessage(PrivateMessage event) {
        if (event.getError() != null) {
            System.out.println(event.getError());
        } else {
            System.out.println(event.getSender().getName() + " said to " + event.getRecepient().getName() + " : " + event.getMessage());
        }
    }
    
    @Subscribe
    public void handlePublicMessage(PublicMessage event) {
        if (event.getError() != null) {
            System.out.println(event.getError());
        } else {
            System.out.println(event.getSender().getName() + " said to everybody : " + event.getMessage());
        }
    }
    
    @Subscribe
    public void handlePresence(Presence event) {
        if (event.getError() != null) {
            System.out.println(event.getError());
        } else {
            if (event.isOnline()) {
                client.getUserManager().addUser(event.getUser());
            } else {
                client.getUserManager().removeUser(event.getUser().getId());
            }
            
            System.out.println(event.getUser() + (event.isOnline()?" just logged in.":" just logged out."));
        }
    }
    

}
