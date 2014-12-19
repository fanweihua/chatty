/**
 * 
 */
package com.funplus.chatty.controller;

import com.funplus.chatty.ChatServer;
import com.funplus.chatty.entity.User;
import com.funplus.chatty.message.LoginRequest;
import com.funplus.chatty.message.LoginResponse;
import com.funplus.chatty.message.PresenceResponse;
import com.funplus.chatty.message.PrivateMessageRequest;
import com.funplus.chatty.message.PrivateMessageResponse;
import com.funplus.chatty.message.PublicMessageRequest;
import com.funplus.chatty.message.PublicMessageResponse;
import com.google.common.eventbus.Subscribe;

import io.netty.channel.Channel;

import java.util.List;

/**
 * @author Weihua Fan
 *
 */
public class RequestController {

    private ChatServer chatServer;

    public RequestController(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    @Subscribe
    public void handleLogin(LoginRequest login) {
        List<User> userList = chatServer.getUserManager().getUserList();
        User self = chatServer.getUserManager().createUser(login);
        chatServer.getUserManager().addUser(self);

        LoginResponse response = new LoginResponse(self, userList);
        self.getSession().getSocketChannel().writeAndFlush(response.build());

        PresenceResponse presence = new PresenceResponse(self, true);
        for (User user : userList) {
            Channel channel = user.getSession().getSocketChannel();
            channel.writeAndFlush(presence.build());
        }
        
        System.out.println(self.getId()+":"+self.getName() + " logged in.");
    }

    @Subscribe
    public void handlePrivateMessage(PrivateMessageRequest privateMessage) {
        User rc = privateMessage.getRecepient();
        PrivateMessageResponse response = new PrivateMessageResponse(privateMessage.getFrom(), rc,
                privateMessage.getMessage());
        rc.getSession().getSocketChannel().writeAndFlush(response.build());
    }
    
    @Subscribe
    public void handlePublicMessage(PublicMessageRequest publicMessage) {
        List<User> userList = chatServer.getUserManager().getUserList();
        PublicMessageResponse response = new PublicMessageResponse(publicMessage.getFrom(),
                publicMessage.getMessage());
        for (User user : userList) {
            if (user.getId() != publicMessage.getFrom().getId()) {
                user.getSession().getSocketChannel().writeAndFlush(response.build());
            }
        }
    }
}
