/** 
 * Project Name:deploy 
 * File Name:ServerHandler.java 
 * Package Name:cn.i7baoz.blog.ende1 
 * Date:2018年2月8日下午2:00:31 
 * 
 */  
  
package cn.i7baoz.blog.decoder.limitDecoder;  

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/** 
 * ClassName:ServerHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午2:00:31 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String mess = (String) msg;
		System.out.println("Server reve data : " + mess);
		ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("server to u " + mess + "&_").getBytes()));
//			.addListener(ChannelFutureListener.CLOSE);
			
	}
	
	

}
 