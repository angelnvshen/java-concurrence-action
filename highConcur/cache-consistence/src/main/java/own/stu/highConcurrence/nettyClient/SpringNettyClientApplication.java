package own.stu.highConcurrence.nettyClient;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import own.stu.highConcurrence.netty.server.EchoServer;

@SpringBootApplication
public class SpringNettyClientApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Autowired
    private Client client;

    public static void main(String[] args) {
        SpringApplication.run(SpringNettyClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ChannelFuture future = client.start(url, port);;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                client.close();
            }
        });
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        future.channel().closeFuture().syncUninterruptibly();
    }
}
