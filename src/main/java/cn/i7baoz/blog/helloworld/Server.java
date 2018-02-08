/** 
 * Project Name:deploy 
 * File Name:Server.java 
 * Package Name:cn.i7baoz.blog.deploy.deploy 
 * Date:2018年2月8日上午9:42:31 
 * 
 */  
  
package cn.i7baoz.blog.helloworld;  

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
 * ClassName:Server 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 上午9:42:31 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Server {

	public Server() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		ServerBootstrap b = new ServerBootstrap();
		try {
			b.group(boss, worker)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.SO_SNDBUF, 3 * 1024)
				.option(ChannelOption.SO_RCVBUF, 3 * 1024 )
				.option(ChannelOption.SO_BACKLOG, 1024)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new ServerHandler());
					}
				});
			System.out.println("开始启动8765端口");
			ChannelFuture c = b.bind(8765).sync();
			c.channel().closeFuture().sync();
			
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
	public static void main(String[] args) {
		new Server();
	}
}
 