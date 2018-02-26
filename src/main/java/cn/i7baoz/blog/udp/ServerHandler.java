/** 
 * Project Name:deploy 
 * File Name:ServerHandler.java 
 * Package Name:cn.i7baoz.blog.udp 
 * Date:2018年2月26日下午3:36:42 
 * 
 */  
  
package cn.i7baoz.blog.udp;  

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/** 
 * ClassName:ServerHandler 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月26日 下午3:36:42 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg)
			throws Exception {
		
		String req = msg.content().toString(CharsetUtil.UTF_8);
		
		System.out.println(req);
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		super.channelRead(ctx, msg);
	}
	
	

}
 