package own.stu.netty.rpcsim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import own.stu.netty.rpcsim.server.RpcServer;

@SpringBootApplication
public class RpcSimApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RpcSimApplication.class, args);
    }

    @Autowired
    private RpcServer rpcServer;

    @Override
    public void run(String... args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                rpcServer.destroy();
            }
        });
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        //future.channel().closeFuture().syncUninterruptibly();
    }
}
