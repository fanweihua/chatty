/**
 * 
 */
package com.funplus.chatty;

import org.json.JSONArray;
import org.json.JSONObject;

import com.funplus.chatty.entity.User;
import com.funplus.chatty.event.ConnectEvent;
import com.funplus.chatty.event.Heartbeat;
import com.funplus.chatty.event.LoginEvent;
import com.funplus.chatty.event.Presence;
import com.funplus.chatty.event.PrivateMessage;
import com.funplus.chatty.event.PublicMessage;
import com.funplus.chatty.message.Request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weihua Fan
 *
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<JSONObject> {
    
    private ChatClient client;
    
    public ChatClientHandler(ChatClient client) {
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        client.setChannel(ctx.channel());
        client.postEvent(new ConnectEvent(ctx.channel()));
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JSONObject msg) throws Exception {
        int actionId = msg.getInt("a");
        switch (actionId) {
        case Request.Login:
            JSONObject userObject = msg.getJSONObject("u");
            User self = User.newFromJsonObject(userObject);
            JSONArray userArray = msg.getJSONArray("ul");
            List<User> userList = new ArrayList<>();
            for (int i=0; i<userArray.length();i++) {
                userList.add(User.newFromJsonObject(userArray.getJSONObject(i)));
            }
            LoginEvent login = new LoginEvent(self, userList);
            client.postEvent(login);
            break;
        case Request.PrivateMessage:
            User sender = client.getUserById(msg.getInt("sd"));
            User recepient = client.getUserById(msg.getInt("rc"));
            String message = msg.getString("m");
            PrivateMessage pm = new PrivateMessage(sender, recepient, message);
            client.postEvent(pm);
            break;
        case Request.PublicMessage: 
            User pubSender = client.getUserById(msg.getInt("sd"));
            String pubMsg = msg.getString("m");
            PublicMessage pum = new PublicMessage(pubSender, pubMsg);
            client.postEvent(pum);
            break;
        case Request.Presence:
            User presencer = User.newFromJsonObject(msg.getJSONObject("u"));
            boolean online = msg.getBoolean("p");
            Presence presence = new Presence(presencer, online);
            client.postEvent(presence);
            break;
        case Request.Heartbeat:
            Heartbeat hb = new Heartbeat();
            client.postEvent(hb);
            break;
        }
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        client.postEvent(new ConnectEvent(cause.getMessage()));
    }

}
