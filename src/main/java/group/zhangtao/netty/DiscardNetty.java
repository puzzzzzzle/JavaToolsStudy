package group.zhangtao.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;


public class DiscardNetty extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in  = (ByteBuf) msg;
        try {
            System.out.println(in.toString(CharsetUtil.US_ASCII));
        }
        finally {
            in.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx,close();
        cause.printStackTrace();
    }
}
