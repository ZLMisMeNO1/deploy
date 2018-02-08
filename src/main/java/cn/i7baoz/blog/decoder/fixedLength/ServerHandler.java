/** 
 * Project Name:deploy 
 * File Name:ServerHandler.java 
 * Package Name:cn.i7baoz.blog.decoder.fixedLength 
 * Date:2018年2月8日下午4:00:44 
 * 
 */  
  
package cn.i7baoz.blog.decoder.fixedLength;  

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/** 
 * ClassName:ServerHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午4:00:44 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		String mess = (String)msg;
		
		System.out.println("server receive message : " + mess );
		
		ctx.channel().writeAndFlush(Unpooled.copiedBuffer( ("hello client , i reveive your message " + mess).getBytes() ));
	}

	
}
 