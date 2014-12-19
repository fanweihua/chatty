/**
 * 
 */
package com.funplus.chatty.controller;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Weihua Fan
 *
 */
public class SessionManager {
    
    private AtomicInteger idGenerator = new AtomicInteger(0);
    //private List<Session> sessions;
    private Map<Integer, Session> sessionsById;
    private Map<SocketChannel, Session> sessionsByChannel;
    
    public SessionManager() {
        this.sessionsByChannel = new ConcurrentHashMap<>();
        this.sessionsById = new ConcurrentHashMap<>();
    }
    
    public Session createSession(SocketChannel socketChannel) {
      Session session = new Session();
      session.setId(idGenerator.incrementAndGet());
      session.setSessionManager(this);
      session.setSocketChannel(socketChannel);
      //session.setMaxIdleTime(this.engine.getConfiguration().getDefaultMaxSessionIdleTime());
      //session.setNodeId(this.config.isClustered() ? this.engine.getClusterManager().getLocalNodeName() : "");
      //session.setType(SessionType.DEFAULT);
      //session.setReconnectionSeconds(this.engine.getConfiguration().getGlobalReconnectionSeconds());

      //IPacketQueue packetQueue = new NonBlockingPacketQueue(this.engine.getConfiguration().getSessionPacketQueueMaxSize());
      //packetQueue.setPacketQueuePolicy(this.packetQueuePolicy);
      //session.setPacketQueue(packetQueue);

      return session;
    }
    
    public void addSession(Session session) {
        sessionsById.put(session.getId(), session);
        sessionsByChannel.put(session.getSocketChannel(), session);
    }
    
    public Session getSessionByChannel(SocketChannel channel) {
        return sessionsByChannel.get(channel);
    }
}
