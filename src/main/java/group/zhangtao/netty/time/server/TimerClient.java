package group.zhangtao.netty.time.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimerClient extends ChannelHandlerAdapter {
    public static void main(String[] args) {
        try {
            new TimerClient().start("127.0.0.1",8081);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void start(String host,int port) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap =new  Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandel());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host,port).sync();

            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
    private class ClientHandel extends ChannelHandlerAdapter{
        private String msg = "now";
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            byte[] bytes = msg.getBytes();
            ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf)msg;
            byte[]bytes= new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String resp = new String(bytes,"UTF-8");
            System.out.println(resp);
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
            cause.printStackTrace();
        }
    }
}
