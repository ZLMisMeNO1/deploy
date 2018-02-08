/** 
 * Project Name:deploy 
 * File Name:Client.java 
 * Package Name:cn.i7baoz.blog.ende1 
 * Date:2018年2月8日下午2:03:18 
 * 
 */  
  
package cn.i7baoz.blog.decoder.limitDecoder;  

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/** 
 * ClassName:Client 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月8日 下午2:03:18 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Client {

	public Client() {
		EventLoopGroup c = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		
		b.group(c)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ByteBuf buf = Unpooled.copiedBuffer("&_".getBytes());
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new ClientHandler());
			}
			
			
			
		});
		try {
			ChannelFuture f = b.connect("127.0.0.1", 8765).sync();
			
			f.channel().writeAndFlush(Unpooled.copiedBuffer("aaa&_".getBytes()));
			f.channel().writeAndFlush(Unpooled.copiedBuffer("bbbbbb&_".getBytes()));
			f.channel().writeAndFlush(Unpooled.copiedBuffer("ccccccccccc&_".getBytes()));
			f.channel().closeFuture().sync();
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			c.shutdownGracefully();
		}
	}
	public static void main(String[] args) {
		new Client();
	}
}
 