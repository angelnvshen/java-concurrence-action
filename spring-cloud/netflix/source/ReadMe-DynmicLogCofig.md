### springboot 2.x 项目动态日志配置

#### 1:pom.xml 开启监控管理功能

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 2:配置文件开启日志访问节点 endpoint

```properties
management:
  endpoints:
    web:
      exposure:
        include: health,info,service-registry,loggers
```

#### 3:访问日志节点

http://localhost:8071/actuator/loggers，

```json
{
"levels": [
"OFF",
"ERROR",
"WARN",
"INFO",
"DEBUG",
"TRACE"
],
"loggers": {
....
"own.stu.springcloud.product.controller.ProductController": {
"configuredLevel": "WARN",
"effectiveLevel": "WARN"
}
}
}
```

如果需要配置单个访问路径的日志界别，可以发送post请求如下：

http://localhost:8071/actuator/loggers/own.stu.springcloud.product.controller.ProductController

{
	"configuredLevel":"debug"
}

如果想永久的配置日志的级别还是需要通过`logging.level.package-path` 来进行配置。

