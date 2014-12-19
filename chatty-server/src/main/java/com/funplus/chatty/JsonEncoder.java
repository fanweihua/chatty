/**
 * 
 */
package com.funplus.chatty;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.nio.CharBuffer;
import java.util.List;

/**
 * @author Weihua Fan
 *
 */
@Sharable
public class JsonEncoder extends MessageToMessageEncoder<JSONObject> {
    
    private ObjectMapper mapper;

    public JsonEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, JSONObject msg, List<Object> out) throws Exception {
        String json = mapper.writeValueAsString(msg);
        out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(json), CharsetUtil.UTF_8));
        
    }

}
