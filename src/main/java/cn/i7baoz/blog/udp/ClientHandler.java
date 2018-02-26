/** 
 * Project Name:deploy 
 * File Name:ClientHandler.java 
 * Package Name:cn.i7baoz.blog.udp 
 * Date:2018年2月26日下午3:28:22 
 * 
 */  
  
package cn.i7baoz.blog.udp;  

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/** 
 * ClassName:ClientHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月26日 下午3:28:22 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg)
			throws Exception {
		
		String req = msg.content().toString(CharsetUtil.UTF_8);
		
		System.out.println(req);
		
	}

}
 