/** 
 * Project Name:deploy 
 * File Name:ServerHandler.java 
 * Package Name:cn.i7baoz.blog.deploy.deploy 
 * Date:2018年2月8日上午9:47:15 
 * 
 */  
  
package cn.i7baoz.blog.helloworld;  

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/** 
 * ClassName:ServerHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 上午9:47:15 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		ByteBuf buf = (ByteBuf)msg;
		
		byte[] b = new byte[buf.readableBytes()];
		
		buf.readBytes(b);
		
		String clientMsg = new String(b,"utf-8");
		
		System.out.println("server端收到消息:" + clientMsg );
		
		String serverMsg = "你好客户端，我已经接收到你的消息[" +clientMsg+ "]";
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(serverMsg.getBytes()))
			.addListener(ChannelFutureListener.CLOSE);
	}

	
}
 