/**
 * 
 */
package com.funplus.chatty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Weihua Fan
 *
 */
public abstract class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    protected ObjectMapper mapper;
    
    public ChatServerInitializer() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
    }
    
}
