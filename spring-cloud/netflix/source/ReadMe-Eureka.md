# Getting Started

### 1: springcloud source code 

#### A：eureka-server

**为什么要自我保护**

1.因为同时保留"**好数据**"与"**坏数据**"总比丢掉任何数据要更好，当网络故障恢复后，这个 Eureka 节点会退出"自我保护模式"。 
2.Eureka 还有客户端缓存功能(也就是微服务的缓存功能)。即便 Eureka 集群中所有节点都宕机失效，微服务的 Provider 和 Consumer都能正常通信。  
3.微服务的[负载均衡](https://cloud.tencent.com/product/clb?from=10680)策略会自动剔除死亡的微服务节点。

#####优雅上下线

**方案一：**

调用Eureka的接口，让Eureka自动剔除该服务IP。

获取服务的AppID和InstanceID，分别对应响应中的`<app>`和`instanceId`

```java
curl -X GET \
  http://IP:port/eureka/apps/

从可用服务列表中剔除该服务
Copy
curl -X PUT \
  'http://IP:port/eureka/apps/AppID/InstanceID/status?value=DOWN'

将该服务加到可用服务列表中
Copy
curl -X PUT \
  'http://IP:port/eureka/apps/AppID/InstanceID/status?value=UP' \
```

**方案二 :**

调用服务接口，服务向Eureka报告为不可用状态

项目中增加配置,重点是service-registry

```
management.endpoints.web.exposure.include=health,info,service-registry
部署前执行命令
Copy
curl -X POST \
  'http://IP:port/actuator/service-registry?status=DOWN' \
  -H 'content-type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8' \

部署后执行命令
curl -X POST \
  'http://IP:port/actuator/service-registry?status=UP' \
  -H 'content-type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8' \
```

#### 服务注册中心

- 服务同步

> 同一个服务的两个实例如果注册到不同的服务中心实例上，由于服务注册中心之间互相注册为服务，所以服务中心之间会互相转发注册请求服务给集群中的其他服务注册中心，从而实现服务注册中心之间的服务同步。

- 失效剔除

> sometime,服务实例会因为内存溢出、网络故障等不正常下线了。但是服务注册中心并没有收到“服务下线”的请求。那么为了解决这个问题，Eureca Server 在启动的时候会创建一个定时任务，默认每隔六十秒将当前清单中超时（默认九十秒）没有续约的服务剔除出去。

- 自我保护

> EurekaServer会统计心跳失败的比例在15分钟之内是否低于85%，如果出现低于的情况，EurekaServer会将这些实例保护起来，让其不过期，但是这样会让客户端拿到已经挂掉的服务实例，这就要求客户端必须要有容错机制（请求重试、断路器等） eureka.server.enable-self-preservation=false ： 关闭保护机制