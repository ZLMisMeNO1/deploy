/** 
 * Project Name:deploy 
 * File Name:Server.java 
 * Package Name:cn.i7baoz.blog.decoder.fixedLength 
 * Date:2018年2月8日下午3:53:34 
 * 
 */  
  
package cn.i7baoz.blog.decoder.fixedLength;  

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/** 
 * ClassName:Server 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午3:53:34 
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
		
		b.group(boss,worker)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.SO_RCVBUF, 3 * 1024)
			.option(ChannelOption.SO_SNDBUF, 3 * 1024)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipe = ch.pipeline();
					//定长5个长度
					pipe.addLast(new FixedLengthFrameDecoder(5));
					pipe.addLast(new StringDecoder());
					pipe.addLast(new ServerHandler());
				}
				
			});
		
		try {
			ChannelFuture future = b.bind(8765).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e ) {
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
 