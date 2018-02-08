/** 
 * Project Name:deploy 
 * File Name:Client.java 
 * Package Name:cn.i7baoz.blog.deploy.deploy 
 * Date:2018年2月8日上午9:53:01 
 * 
 */  
  
package cn.i7baoz.blog.helloworld;  

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/** 
 * ClassName:Client 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 上午9:53:01 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Client {

	public Client() {
		EventLoopGroup c = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.channel(NioSocketChannel.class)
			.group(c)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientHandler());
				}
			});
			ChannelFuture f = b.connect("127.0.0.1", 8765).sync();
			f.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Server".getBytes()));
			f.channel().closeFuture().sync();
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			c.shutdownGracefully();
		}
	}
	public static void main(String[] args) {
		new Client();
	}
}
 