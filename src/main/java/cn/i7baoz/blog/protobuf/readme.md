
## 编解码技术

说白了就是java序列化技术，序列化的目的就2个，第一是网络传输，第二是对象持久化

虽然我们可以使用java进行对象序列化，netty去传输，但是java序列化的硬伤太多，比如Java序列化没法跨语言、序列化后码流太大、序列化性能太低...

主流的编解码框架：

	1. JBoss的Marshalling
	2. google的Protobuf
	3. 基于Protobuf的Kyto
	4. MessagePack框架