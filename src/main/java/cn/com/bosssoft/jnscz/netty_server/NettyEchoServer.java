package cn.com.bosssoft.jnscz.netty_server;

import cn.com.bosssoft.jnscz.netty_server.handler.ReplyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Hello world!
 *
 */
public class NettyEchoServer 
{
	private int port;
	
	public NettyEchoServer(int port) {
		this.port = port;
	}
	
	public void runServer() throws Exception {
		 EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap(); // (2)
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class) // (3)
	             .childHandler(new ChannelInitializer<SocketChannel>() {
	            
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
	                     ch.pipeline().addLast(new ReplyHandler());
					} // (4)
	                 
	             })
	             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
	             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

	            // 绑定端口，开始接收进来的连接
	            ChannelFuture f = b.bind(port).sync(); // (7)

	            // 等待服务器  socket 关闭 。
	            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
	        }
	}
	
    public static void main( String[] args ) throws Exception
    {
    	System.out.println("Server ready.");
    	int port = 8080;
    	new NettyEchoServer(port).runServer();
    }
}
