### 拆包和粘包的问题

我们假设操作系统的tcp/ip协议栈已经接受了3个数据包：

>	ABC DEF GHI
	
由于基于流传输的协议的这种普通的性质，在你的应用程序里读取数据的时候会有很高的可能性数据被分为下面的片段

>	AB CDEFG H I
	
因此，一个接受方不管他是客户端还是服务端，都应该把接收到的数据整理成一个或者多个更有意思并且能够让程序的业务逻辑更好理解的数据，
在上面的例子中，接受到的数据应该被构造成下面的格式

>	ABC DEF GHI

#### 分析TCP粘包、拆包问题的产生原因

	1. 应用程序write写入的字节大小大于套接口发送缓存区的大小
	2. 进行MSS大小的TCP分段
	3. 以太网帧的payload大于MTU进行IP分片
	
#### 解决方法

粘包拆包问题的解决方案，根据业界主流协议的有三种方案：

	1. 消息定长，例如每个报文的大小固定在200字节，如果不够，需要用空格补位。
		- 如果长度不够还没补全的话，可能会出现接收到的信息不完整
	2. 在包尾部增加特殊字符进行分割，例如加回车等。
		- 比较常用
	3. 将消息分为消息头和消息体，在消息头中包含表示消息总长度的字段，然后进行业务逻辑的处理。
		- 安全
	
	
##### 第一种情况	
	
```

ChannelPipeline pipe = ch.pipeline();

//定长5个长度

pipe.addLast(new FixedLengthFrameDecoder(5));


```
	
	
##### 第二种情况	
在Server和Client中加入

```
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
```

