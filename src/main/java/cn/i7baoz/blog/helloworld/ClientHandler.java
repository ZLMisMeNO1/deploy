/** 
 * Project Name:deploy 
 * File Name:ClientHandler.java 
 * Package Name:cn.i7baoz.blog.deploy.deploy 
 * Date:2018年2月8日上午11:02:17 
 * 
 */  
  
package cn.i7baoz.blog.helloworld;  

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/** 
 * ClassName:ClientHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 上午11:02:17 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		ByteBuf b = (ByteBuf) msg;
		
		byte[] buf = new byte[b.readableBytes()];
		
		b.readBytes(buf);
		
		String req = new String(buf,"utf-8");
		System.out.println("客户端收到的信息:" + req);
		ctx.writeAndFlush(Unpooled.copiedBuffer(("你好服务端，我是客户端，我收到你的消息是" + req).getBytes()));
	}

	
}
 