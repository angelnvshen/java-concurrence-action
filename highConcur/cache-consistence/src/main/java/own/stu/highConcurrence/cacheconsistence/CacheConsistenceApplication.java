package own.stu.highConcurrence.cacheconsistence;

import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import own.stu.highConcurrence.cacheconsistence.nettyServer.IdServer;

@SpringBootApplication
@MapperScan("own.stu.highConcurrence.cacheconsistence.dao")
public class CacheConsistenceApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Autowired
    private IdServer idServer;

    public static void main(String[] args) {
        SpringApplication.run(CacheConsistenceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ChannelFuture future = idServer.start(url, port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> idServer.destroy()));
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        future.channel().closeFuture().syncUninterruptibly();
    }
}
