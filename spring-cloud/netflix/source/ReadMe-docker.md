### spring-boot docker 

Dockerfile：

```java
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

pom.xml

```xml
<build>
    <finalName>eureka-server</finalName>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>dockerfile-maven-plugin</artifactId>
            <version>1.4.8</version>
            <configuration>
                <!--docker 镜像名称 -->
                <repository>${project.groupId}/${project.artifactId}</repository>
                <buildArgs>
                    <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                </buildArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

执行打包：mvn install dockerfile:build。

#### 推送 私有仓库，及拉去

1. 登录阿里云Docker Registry

```shell
$ sudo docker login --username=angelnvshen@163.com registry.cn-shanghai.aliyuncs.com
```

用于登录的用户名为阿里云账号全名，密码为开通服务时设置的密码。

您可以在产品控制台首页修改登录密码。

2. 从Registry中拉取镜像

```shell
$ sudo docker pull registry.cn-shanghai.aliyuncs.com/spring-cloud-master/learn:[镜像版本号]
```

3. 将镜像推送到Registry

```shell
$ sudo docker login --username=angelnvshen@163.com registry.cn-shanghai.aliyuncs.com
$ sudo docker tag [ImageId] registry.cn-shanghai.aliyuncs.com/spring-cloud-master/learn:[镜像版本号]
$ sudo docker push registry.cn-shanghai.aliyuncs.com/spring-cloud-master/learn:[镜像版本号]
```

请根据实际镜像信息替换示例中的[ImageId]和[镜像版本号]参数。

4. 选择合适的镜像仓库地址

从ECS推送镜像时，可以选择使用镜像仓库内网地址。推送速度将得到提升并且将不会损耗您的公网流量。

如果您使用的机器位于VPC网络，请使用 registry-vpc.cn-shanghai.aliyuncs.com 作为Registry的域名登录，并作为镜像命名空间前缀。

5. 示例

使用"docker tag"命令重命名镜像，并将它通过专有网络地址推送至Registry。

```shell
$ sudo docker images
REPOSITORY                                                         TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
registry.aliyuncs.com/acs/agent                                    0.7-dfb6816         37bb9c63c8b2        7 days ago          37.89 MB
$ sudo docker tag 37bb9c63c8b2 registry-vpc.cn-shanghai.aliyuncs.com/acs/agent:0.7-dfb6816	
```

使用"docker images"命令找到镜像，将该镜像名称中的域名部分变更为Registry专有网络地址。

```shell
$ sudo docker push registry-vpc.cn-shanghai.aliyuncs.com/acs/agent:0.7-dfb6816
```