/** 
 * Project Name:deploy 
 * File Name:Client.java 
 * Package Name:cn.i7baoz.blog.decoder.fixedLength 
 * Date:2018年2月8日下午4:01:14 
 * 
 */  
  
package cn.i7baoz.blog.decoder.fixedLength;  

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/** 
 * ClassName:Client 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午4:01:14 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Client {

	public Client() {
		EventLoopGroup c = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap ();
		b.group(c)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipe = ch.pipeline();
				pipe.addLast(new FixedLengthFrameDecoder(5));
				pipe.addLast(new StringDecoder());
				pipe.addLast(new ClientHandler());
			}
			
		});
		
		try {
			ChannelFuture future = b.connect("127.0.0.1", 8765).sync();
			future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello Server".getBytes()));
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} finally {
			c.shutdownGracefully();
		}
	}
	public static void main(String[] args) {
		new Client();
	}
}
 