### springBoot

#### Features 来自官网

- Create stand-alone Spring applications
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Provide opinionated 'starter' dependencies to simplify your build configuration
- Automatically configure Spring and 3rd party libraries whenever possible
- Provide production-ready features such as metrics, health checks and externalized configuration
- Absolutely no code generation and no requirement for XML configuration

`SpringBoot` 是为了简化 `Spring` 应用的创建、运行、调试、部署等一系列问题而诞生的产物，**自动装配的特性让我们可以更好的关注业务本身而不是外部的XML配置，我们只需遵循规范，引入相关的依赖就可以轻易的搭建出一个 WEB 工程**。



#### Async 异步

Enables Spring's asynchronous method execution capability, similar to functionality found in Spring's <task:*> XML namespace.

By default, Spring will be searching for an associated thread pool definition: either a unique org.springframework.core.task.TaskExecutor bean in the context, or an java.util.concurrent.Executor bean named "taskExecutor" otherwise. If neither of the two is resolvable, a org.springframework.core.task.SimpleAsyncTaskExecutor will be used to process async method invocations. Besides, annotated methods having a void return type cannot transmit any exception back to the caller. By default, such uncaught exceptions are only logged.

####Starter

自动配置的关键几步以及相应的注解总结如下：

@Configuration&与@Bean------>>>基于java代码的bean配置
@Conditional-------->>>>>>设置自动配置条件依赖
@EnableConfigurationProperties与@ConfigurationProperties------>>>读取配置文件转换为bean。
@EnableAutoConfiguration、@AutoConfigurationPackage 与@Import------>>>实现bean发现与加载。



#### actuator 服务监控与管理

`actuator`是`spring boot`项目中非常强大一个功能，有助于对应用程序进行监视和管理，通过 `restful api` 请求来监管、审计、收集应用的运行情况，针对微服务而言它是必不可少的一个环节

#####endpoint

`actuator` 的核心部分，它用来监视应用程序及交互，`spring-boot-actuator`中已经内置了非常多的 **Endpoints（health、info、beans、httptrace、shutdown等等）**，同时也允许我们自己扩展自己的端点

| id                 | desc                                                         | Sensitive |
| :----------------- | :----------------------------------------------------------- | :-------- |
| **auditevents**    | 显示当前应用程序的审计事件信息                               | Yes       |
| **beans**          | 显示应用Spring Beans的完整列表                               | Yes       |
| **caches**         | 显示可用缓存信息                                             | Yes       |
| **conditions**     | 显示自动装配类的状态及及应用信息                             | Yes       |
| **configprops**    | 显示所有 @ConfigurationProperties 列表                       | Yes       |
| **env**            | 显示 ConfigurableEnvironment 中的属性                        | Yes       |
| **flyway**         | 显示 Flyway 数据库迁移信息                                   | Yes       |
| **health**         | 显示应用的健康信息（未认证只显示`status`，认证显示全部信息详情） | No        |
| **info**           | 显示任意的应用信息（在资源文件写info.xxx即可）               | No        |
| **liquibase**      | 展示Liquibase 数据库迁移                                     | Yes       |
| **metrics**        | 展示当前应用的 metrics 信息                                  | Yes       |
| **mappings**       | 显示所有 @RequestMapping 路径集列表                          | Yes       |
| **scheduledtasks** | 显示应用程序中的计划任务                                     | Yes       |
| **sessions**       | 允许从Spring会话支持的会话存储中检索和删除用户会话。         | Yes       |
| **shutdown**       | 允许应用以优雅的方式关闭（默认情况下不启用）                 | Yes       |
| **threaddump**     | 执行一个线程dump                                             | Yes       |
| **httptrace**      | 显示HTTP跟踪信息（默认显示最后100个HTTP请求 - 响应交换）     | Yes       |

`Spring Boot2.x`中，默认只开放了`info、health`两个端点，剩余的需要自己通过配置`management.endpoints.web.exposure.include`属性来加载（有`include`自然就有`exclude`，不做详细概述了）。如果想单独操作某个端点可以使用`management.endpoint.端点.enabled`属性进行启用或禁用。

Endpoint的加载还是要依靠spring.factories实现的，Spring-boot-actutor包下的META-IN/spring.factories配置了EndpointAutoConfiguration。