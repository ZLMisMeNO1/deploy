/** 
 * Project Name:deploy 
 * File Name:Global.java 
 * Package Name:cn.i7baoz.blog.websocket 
 * Date:2018年2月26日下午3:59:15 
 * 
 */  
  
package cn.i7baoz.blog.websocket;  
/** 
 * ClassName:Global 
 * Function: TODO ADD FUNCTION. 
 * Date:     2018年2月26日 下午3:59:15 
 * @author   baoqi.zhang 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
import io.netty.channel.group.ChannelGroup;  
import io.netty.channel.group.DefaultChannelGroup;  
import io.netty.util.concurrent.GlobalEventExecutor;  
public class Global {  
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);  
      
}   
 