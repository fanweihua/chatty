/**
 * 
 */
package com.funplus.chatty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.funplus.chatty.controller.RequestController;
import com.funplus.chatty.controller.SessionManager;
import com.funplus.chatty.entity.UserManager;
import com.google.common.eventbus.EventBus;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Weihua Fan
 *
 */
public class ChatServer {
    
    private static final Logger log = LoggerFactory.getLogger(ChatServer.class);
    private final int port;
    
    private EventBus eventBus;
    private SessionManager sessionManager;
    private RequestController requestController;
    private UserManager userManager;
    
    public ChatServer(int port) {
        this.port = port;
        this.eventBus = new EventBus();
        this.sessionManager = new SessionManager();
        this.requestController = new RequestController(this);
        this.eventBus.register(requestController);
        this.userManager = new UserManager();
    }
    
    public void run() throws InterruptedException  {
        log.info("Chat server is listening on port {}.", port);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
             .childHandler(new ChatServerInitializer() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(20480, 0, 4, 0, 4));
                    pipeline.addLast("jsonDecoder", new JsonDecoder(mapper));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("jsonEncoder", new JsonEncoder(mapper));
                    pipeline.addLast(new ChatServerHandler(ChatServer.this));
                }
                 
             });
            b.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

}
