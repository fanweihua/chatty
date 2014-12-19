/**
 * 
 */
package com.funplus.chatty;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author Weihua Fan
 *
 */
@Sharable
public class JsonDecoder extends MessageToMessageDecoder<ByteBuf> {
    
    private ObjectMapper mapper;

    public JsonDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        JSONObject ob = mapper.readValue(msg.toString(CharsetUtil.UTF_8), JSONObject.class); 
        out.add(ob);
    }

}
