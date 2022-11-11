# net-framework-public
基于 netty 封装的超简单通俗易用 服务端客户端交互框架 《net-framework》原理，源码和使用说明,开箱即用，只需要开发业务逻辑，完全自定义无限扩充

点击查看原文介绍[《基于 netty 封装的超简单通俗易用 服务端客户端交互框架 《net-framework》原理，源码和使用说明,开箱即用，只需要开发业务逻辑，完全自定义无限扩充》](https://blog.csdn.net/madness1010/article/details/127809201)
## 什么是bio,nio,aio
----------------------------
## 什么是netty
#### Netty是业界最流行的NIO框架之一，它的健壮性、功能、性能、可定制性和可扩展性在同类框架中都是首屈一指的
#### 它已经得到成百上千的商用项目验证，例如Hadoop的RPC框架Avro就使用了Netty作为底层通信
#### 信框架，其他还有业界主流的RPC框架，也使用Netty来构建高性能的异步通信能力。

-----------------------------
## 为什么选择netty
#### 通过对Netty的分析，我们将它的优点总结如下。
#### ◎ API使用简单，开发门槛低；
#### ◎ 功能强大，预置了多种编解码功能，支持多种主流协议；
#### ◎ 定制能力强，可以通过ChannelHandler对通信框架进行灵活地扩展；
#### ◎ 性能高，通过与其他业界主流的NIO框架对比，Netty的综合性能最优；
#### ◎ 成熟、稳定，Netty修复了已经发现的所有JDK NIO BUG，业务开发人员不需要再为NIO的BUG而烦恼；
#### ◎ 社区活跃，版本迭代周期短，发现的 BUG 可以被及时修复，同时，更多的新功能会加入；
#### ◎ 经历了大规模的商业应用考验，质量得到验证。Netty 在互联网、大数据、网络游戏、企业应用、电信软件等众多行业已经得到了成功商用，证明它已经完全能够满足不同行业的商业应用了。
#### 正是因为这些优点，Netty逐渐成为了Java NIO编程的首选框架。
---------------------------------
## 为什么要基于netty封装本net-framework框架
#### 虽然上面说了netty很多优点，但它本身就是一种框架，提供了无限的可能，我们使用的时候还是需要前后加很多铺垫逻辑的
#### 我们多数使用的时候一般都是客户端和服务端的交互场景，因此我们希望开发时候直接就可以关心自己的业务场景，直接编写
#### 服务端和业务端的的业务代码，数据读取，写入等逻辑不想重复编写，解耦数据和对应处理逻辑，直接可以针对某个数据编写对应的业务逻辑
#### 基于以上思考，我业余时间开发了net-framework 框架，当前数据第一版提交，还很基础，因此可能会有一些问题，后续会不断扩充
------------------------------------------
## net-framework框架介绍
### 整体介绍
#### net-framework是一款基于netty开发的网络通讯框架，封装了数据读写和业务定义的逻辑
### 将所有服务端和业务端传输抽象成了signle（信号），一共有两种形式信号，一种是msgSignal（消息信号）,一种是actionSignal（动作信号），
#### 服务端和客户端只需要继承扩展这两种消息类型即可
#### msg消息一般就是我们的读取，比如系统消息，谁上线了的消息，聊天的公开消息等消息
#### action消息一般就是我们的动作，比如登陆，设置，文件传输等消息
#### 将所有服务端和业务端对msgSignal和actionSignal的处理封装了共通的handler和dispatcher 去处理这些消息
#### 服务端和客户端只需要继承handler
### 并实现针对msgSignal 和actionSignal 的处理即可
---------------------
## 框架处理流程
### 框架处理流程图
### 框架流程处理流程描述
#### 1.使用方添加各种自定义signal 到框架共通
#### 2.在服务端和客户端创建对应signal 的处理handler 并注册入框架
#### 3.服务端或者客户端发送自定义signal，服务端或者客户端接受
#### 4.服务端或者客户端从框架获取到对应的处理handler 并处理
----------------------
### 适用场景
#### 1.聊天室
#### 2.远程控制
#### 3.分布式计算
#### 4.文件传输
#### 5.其他交互场景
---------------------------
### 共通端封装以及关键源码说明
#### 1。整体目录
#### 2. 共通端signal相关类结构和源码说明
#### 3. 共通端dispatcher相关类结构以及相源码码说明
#### 4. 共通端handler相关类结构以及源码说明
#### 5. quene 服务端所有连接客户端channel 相关类结构以及源码说明
#### 6. 启动函数说明
#### 
-----------------------------------
### 如何使用net-framework框架
### 步骤
#### 1.扩展msgSignal和actionSignal
#### 2.扩展dispatcher 
#### 3.扩展handler 
#### 4.common 部分打包或拷贝到自己项目中
#### 5.服务端启动类拷贝并启动
#### 6.客户端启动类拷贝并启动
--------------------------------------------
### 使用 demo示例
#### 场景 ：服务端和客户端连接并发送上线消息demo示例
