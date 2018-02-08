/** 
 * Project Name:deploy 
 * File Name:ClientHandler.java 
 * Package Name:cn.i7baoz.blog.decoder.fixedLength 
 * Date:2018年2月8日下午4:05:13 
 * 
 */  
  
package cn.i7baoz.blog.decoder.fixedLength;  

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/** 
 * ClassName:ClientHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午4:05:13 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ClientHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		String mess = (String) msg ;
		
		System.out.println("client receive message : " + mess);
		
		//释放
		ReferenceCountUtil.retain(msg);
	}

	
}
 