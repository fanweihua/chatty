/**
 * 
 */
package com.funplus.chatty;

import com.funplus.chatty.entity.User;
import com.funplus.chatty.entity.UserManager;
import com.funplus.chatty.event.ConnectEvent;
import com.funplus.chatty.event.EventController;
import com.funplus.chatty.message.LoginRequest;
import com.funplus.chatty.message.PrivateMessageRequest;
import com.funplus.chatty.message.PublicMessageRequest;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author Weihua Fan
 *
 */
public class ChatClient {
    
    private int majVersion = 1;
    private int minVersion = 0;
    private int subVersion = 0;
    private String clientDetails = "Android";
    
    private String host;
    private int port;
    private Bootstrap b;
    private EventLoopGroup workerGroup;
    private Channel channel;
    private EventBus eventBus;
    private EventController controller;
    private UserManager userManager;
    
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.controller = new EventController(this);
        this.eventBus = new EventBus();
        this.eventBus.register(controller);
        this.userManager = new UserManager();
    }
    
    private void connect() {
        b = new Bootstrap();
        workerGroup = new NioEventLoopGroup();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.TCP_NODELAY, true);
        b.handler(new ChatClientInitializer() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(20480, 0, 4, 0, 4));
                pipeline.addLast("jsonDecoder", new JsonDecoder(mapper));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("jsonEncoder", new JsonEncoder(mapper));
                // and then business logic.
                pipeline.addLast(new ChatClientHandler(ChatClient.this));

            }
            
        });
        b.connect(host, port);
    }
    
    public void disconnect() {
        // Send disconnect request & shut down
        
        // The connection is closed automatically on shutdown.
        workerGroup.shutdownGracefully();
    }
    
    public void login(final String name, final String password) {
        eventBus.register(new Object() {
            
            @Subscribe
            public void handleConnect(ConnectEvent event) {
                LoginRequest login = new LoginRequest(name, password);
                channel.writeAndFlush(login.build());
            }
        });
        connect();
    }
    
    public void logout(int id) {
        // Send logout request
        // TODO
        disconnect();
        
    }
    
    public void sendPrivateMessage(String message, int recipientId) {
        User rc = userManager.getUser(recipientId);
        User self = userManager.getSelf();
        PrivateMessageRequest pm = new PrivateMessageRequest(self, rc, message);
        channel.writeAndFlush(pm.build());
    }
    
    public void sendPublicMessage(String message) {
        User self = userManager.getSelf();
        PublicMessageRequest pm = new PublicMessageRequest(self, message);
        channel.writeAndFlush(pm.build());
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void postEvent(Object evt) {
        this.eventBus.post(evt);
    }

    public void addSubscriber(Object subscriber) {
        this.eventBus.register(subscriber);
    }

    public void removeSubscriber(Object subscriber) {
        this.eventBus.unregister(subscriber);
    }
    
    public String getVersion() {
        return this.majVersion + "." + this.minVersion + "." + this.subVersion;
    }
    
    public User getUserById(int id) {
        return userManager.getUser(id);
    }

    public UserManager getUserManager() {
        return userManager;
    }

}
