/** 
 * Project Name:deploy 
 * File Name:Server.java 
 * Package Name:cn.i7baoz.blog.ende1 
 * Date:2018年2月8日下午1:51:53 
 * 
 */  
  
package cn.i7baoz.blog.decoder.limitDecoder;  

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/** 
 * ClassName:Server 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午1:51:53 
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
		
		b.group(boss, worker)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.SO_RCVBUF, 3 * 1024)
			.option(ChannelOption.SO_SNDBUF, 3*1024)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//-------------------------------------------↓
					//这段代码的意思是
					//从客户端发送过来的信息  按照&_分割
					// abcdsfsd&_fsdfsda&_ 会被分割为两个信息 1.abcdsfsd 2._fsdfsda
					//利用特殊字符来解决拆包粘包的问题
					ByteBuf buf = Unpooled.copiedBuffer("&_".getBytes());
					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
					//-------------------------------------------↑
					
					//加入这句话，就可以在handler中 用String来接收信息
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new ServerHandler());
				}
			});
		try {
			ChannelFuture f = b.bind(8765).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}
	public static void main(String[] args) {
		new Server();
	}
}
 