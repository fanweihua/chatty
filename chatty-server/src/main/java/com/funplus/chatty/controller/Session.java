/**
 * 
 */
package com.funplus.chatty.controller;

import io.netty.channel.socket.SocketChannel;

/**
 * @author Weihua Fan
 *
 */
public class Session {
    
    private int id;
    private SocketChannel socketChannel;
    //private DatagramChannel datagramChannel;
    private long readBytes = 0L;
    private long writtenBytes = 0L;
    private long creationTime;
    private long lastReadTime;
    private long lastWriteTime;
    private long lastActivityTime;
    private long lastLoggedInActivityTime;
    private SessionManager sessionManager;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
    public long getReadBytes() {
        return readBytes;
    }
    public void setReadBytes(long readBytes) {
        this.readBytes = readBytes;
    }
    public long getWrittenBytes() {
        return writtenBytes;
    }
    public void setWrittenBytes(long writtenBytes) {
        this.writtenBytes = writtenBytes;
    }
    public long getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
    public long getLastReadTime() {
        return lastReadTime;
    }
    public void setLastReadTime(long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }
    public long getLastWriteTime() {
        return lastWriteTime;
    }
    public void setLastWriteTime(long lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }
    public long getLastActivityTime() {
        return lastActivityTime;
    }
    public void setLastActivityTime(long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }
    public long getLastLoggedInActivityTime() {
        return lastLoggedInActivityTime;
    }
    public void setLastLoggedInActivityTime(long lastLoggedInActivityTime) {
        this.lastLoggedInActivityTime = lastLoggedInActivityTime;
    }
    public SessionManager getSessionManager() {
        return sessionManager;
    }
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    
}
