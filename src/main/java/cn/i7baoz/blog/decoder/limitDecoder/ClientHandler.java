/** 
 * Project Name:deploy 
 * File Name:ClientHandler.java 
 * Package Name:cn.i7baoz.blog.ende1 
 * Date:2018年2月8日下午2:20:07 
 * 
 */  
  
package cn.i7baoz.blog.decoder.limitDecoder;  

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/** 
 * ClassName:ClientHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午2:20:07 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(msg);
		String serverMsg = (String)msg;
		System.out.println("1:"+serverMsg);
		ReferenceCountUtil.release(msg);
	}

	
}
 