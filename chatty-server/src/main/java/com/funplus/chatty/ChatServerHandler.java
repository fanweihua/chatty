/**
 * 
 */
package com.funplus.chatty;

import org.json.JSONObject;

import com.funplus.chatty.controller.Session;
import com.funplus.chatty.controller.SessionManager;
import com.funplus.chatty.entity.User;
import com.funplus.chatty.entity.UserManager;
import com.funplus.chatty.message.LoginRequest;
import com.funplus.chatty.message.PrivateMessageRequest;
import com.funplus.chatty.message.PublicMessageRequest;
import com.funplus.chatty.message.Request;
import com.google.common.eventbus.EventBus;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Weihua Fan
 *
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<JSONObject> {
    
    private EventBus eventBus;
    private SessionManager sessionManager;
    private UserManager userManager;
    
    public ChatServerHandler(ChatServer chatServer) {
        this.eventBus = chatServer.getEventBus();
        this.sessionManager = chatServer.getSessionManager();
        this.userManager = chatServer.getUserManager();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Session session = sessionManager.createSession((SocketChannel)ctx.channel());
        sessionManager.addSession(session);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JSONObject msg) throws Exception {
        int actionId = msg.getInt("a");
        switch (actionId) {
        case Request.Login:
            String name = msg.getString("n");
            String password = msg.getString("pw");
            LoginRequest login = new LoginRequest(name, password);
            login.setId(actionId);
            login.setSender(sessionManager.getSessionByChannel((SocketChannel)ctx.channel()));
            eventBus.post(login);
            break;
        case Request.PrivateMessage:
            User from = userManager.getUserById(msg.getInt("sd"));
            User rc = userManager.getUserById(msg.getInt("rc"));
            String message = msg.getString("m");
            PrivateMessageRequest pmr = new PrivateMessageRequest(from, rc, message);
            pmr.setId(actionId);
            pmr.setSender(sessionManager.getSessionByChannel((SocketChannel)ctx.channel()));
            eventBus.post(pmr);
            break;
        case Request.PublicMessage:
            User pubFrom = userManager.getUserById(msg.getInt("sd"));
            String pubMsg = msg.getString("m");
            PublicMessageRequest pubReq = new PublicMessageRequest(pubFrom, pubMsg);
            pubReq.setId(actionId);
            pubReq.setSender(sessionManager.getSessionByChannel((SocketChannel)ctx.channel()));
            eventBus.post(pubReq);
            break;
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
