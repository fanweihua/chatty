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
public abstract class ChatClientInitializer extends ChannelInitializer<SocketChannel>{
    
    protected ObjectMapper mapper;
    
    public ChatClientInitializer() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
    }

//    @Override
//    protected void initChannel(SocketChannel ch) throws Exception {
//        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(20480, 0, 4, 0, 4));
//        pipeline.addLast("jsonDecoder", new JsonDecoder(mapper));
//        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//        pipeline.addLast("jsonEncoder", new JsonEncoder(mapper));
//        // and then business logic.
//        pipeline.addLast(new ChatClientHandler());
//    }

}
