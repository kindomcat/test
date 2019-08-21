package com.xzl.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/8/20 14:41
 * @version: v0.0.1
 * @history:
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)
    /**
     * 这里我们覆盖了 chanelRead() 事件处理方法。
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用。
     *((ByteBuf) msg).release()：丢弃数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
