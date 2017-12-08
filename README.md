# basic-architecture
基础架构（版本：Dalston.SR1）
</br>1、config-repo：配置文件目录，需要将此目录放在单独的git路径下，要不然会将git下的全都下载下来，这里为了方便，就集中放置;
  另外，因为需要配置webhook，所以在push代码的时候，需要确定只能是配置文件更新才引起webhook发起/bus/refresh/请求，这里暂时不做webhook通知，采用postman测试
</br>2、discovery-server
</br>3、config-server：通过Eureka注册配置管理服务
</br>4、first-service，采用
</br>5、second-service，采用

学习搭建spring cloud的基础服务架构，包含以下几个
</br>1、服务注册与发现高可用集群（或者单点集群，单点容易产生单点故障）
</br>2、高可用配置文件集中管理方案，可以实现配置的自动刷新（可以采用git、spring cloud config、spring cloud bus、kafka或rabbitmq）
</br>3、高可用
</br>4、
</br>
</br>
</br>


学习：
</br>spring cloud 核心是服务治理，具有的特性：
</br>1、配置管理：config
</br>2、服务发现：Eureka
</br>3、断路器：Hystrix
</br>4、智能路由：Zuul
</br>5、负载均衡：Ribbon
</br>6、微代理、
</br>7、控制总线、
</br>8、全局锁、
</br>9、决策竞选、
</br>10、分布式会话
</br>11、集群状态管理


备注：
1、配置默认存储在tmp文件目录，linux系统默认会清理一定时间没有更新的tmp目录下的文件，所以增加以下配置：
  #git的配置文件会加载到本地的目录
  spring.cloud.config.server.git.basedir=D:/git-config
  
  
  
 </br>查阅了很多资料，其他笔者的url如下
 </br><url>https://gitee.com/gongxusheng/spring-config-demo</url>
 </br><url>https://github.com/spring-cloud</url>

