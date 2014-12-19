/**
 * 
 */
package com.funplus.chatty.event;

import io.netty.channel.Channel;

/**
 * @author Weihua Fan
 *
 */
public class ConnectEvent {
    private String error;
    private Channel channel;

    public ConnectEvent(Channel channel) {
        this.channel = channel;
    }

    public ConnectEvent(String error) {
        this.error = error;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
