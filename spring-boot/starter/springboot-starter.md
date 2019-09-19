###自定义springboot-starter （springboot2.x)

**坑1：Maven多模块项目+SpringBoot，编译失败：程序包xxx不存在**

原因分析：

simple-springboot-starter-autoconfigurer 也是SpringBoot工程，SpringBoot工程打包编译时，会生成两种jar包，一种是普通的jar，另一种是可执行jar。

默认情况下，这两种jar的名称相同，在不做配置的情况下，普通的jar先生成，可执行jar后生成，造成可执行jar会覆盖普通的jar。而test-springboot-model工程无法依赖simple-springboot-starter-autoconfigurer 工程的可执行jar，所以编译失败：程序包xxx不存在。



在simple-springboot-starter-autoconfigurer 子工程下的pom.xml，添加以下配置

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<classifier>exec</classifier>
				</configuration>
			</plugin>
		</plugins>



**坑2：加入以上的spring-plugin 时，需要有main入口函数。**

所以不要将SimpleSpringBootStarterApplication 类删除。



####自定义包中的xxx.properties，被其他项目引用是 properties能够被提示的原因是：

有spring-configuration-metadata.json文件。

所以可以在自定义包中引入依赖

```yml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-configuration-processor</artifactId>
  <version>2.0.6.RELEASE</version>
  <optional>true</optional>
</dependency>
```