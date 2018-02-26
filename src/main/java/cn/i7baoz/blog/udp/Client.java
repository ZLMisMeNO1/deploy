/** 
 * Project Name:deploy 
 * File Name:Client.java 
 * Package Name:cn.i7baoz.blog.udp 
 * Date:2018年2月26日下午3:20:38 
 * 
 */  
  
package cn.i7baoz.blog.udp;  

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

/** 
 * ClassName:Client 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月26日 下午3:20:38 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Client {

	public void run( int port ) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap() ;
			b.group(group)
			.channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new ClientHandler());
			
			Channel ch = b.bind(0).sync().channel();
			ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("查询请求",CharsetUtil.UTF_8),
					new InetSocketAddress("255.255.255.255",port))).sync();
			
			if ( !ch.closeFuture().await(15000)) {
				System.out.println("查询超时");
			}
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Client().run(8765);
	}
}
 