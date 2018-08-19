package cn.com.bosssoft.jnscz.netty_server.handler;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ReplyHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
////		System.out.println((ByteBuf)msg);
		ByteBuf repMsgBuf = ((ByteBuf)msg).copy();
		repMsgBuf.clear();
		repMsgBuf.writeBytes("hello".getBytes());
		int len = ((ByteBuf)msg).readableBytes();
		byte[] data = new byte[len];
		((ByteBuf)msg).readBytes(data);
		repMsgBuf.writeBytes(data);
		ctx.writeAndFlush(repMsgBuf);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
}
