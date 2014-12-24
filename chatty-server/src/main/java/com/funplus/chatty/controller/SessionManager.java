/**
 * 
 */
package com.funplus.chatty.controller;

import com.funplus.chatty.entity.Session;

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
      session.setConnected(true);
      return session;
    }
    
    public void addSession(Session session) {
        sessionsById.put(session.getId(), session);
        sessionsByChannel.put(session.getSocketChannel(), session);
    }
    
    public Session getSessionByChannel(SocketChannel channel) {
        return sessionsByChannel.get(channel);
    }
    
    public void removeSession(Session session) {
        sessionsById.remove(session.getId());
        sessionsByChannel.remove(session.getSocketChannel());
    }
}
