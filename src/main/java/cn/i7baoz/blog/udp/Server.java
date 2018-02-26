/** 
 * Project Name:deploy 
 * File Name:Server.java 
 * Package Name:cn.i7baoz.blog.udp 
 * Date:2018年2月26日下午3:33:49 
 * 
 */  
  
package cn.i7baoz.blog.udp;  


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/** 
 * ClassName:Server 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月26日 下午3:33:49 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Server {

	public void run(int port ) throws Exception {
		
		EventLoopGroup boss = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap() ;
			b.group(boss)
			.channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new ServerHandler());
			
			b.bind(port).sync().channel().closeFuture().sync();
			
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Server().run(8765);
	}
}
 