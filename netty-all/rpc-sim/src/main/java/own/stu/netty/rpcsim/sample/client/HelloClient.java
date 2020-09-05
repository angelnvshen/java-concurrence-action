package own.stu.netty.rpcsim.sample.client;

import own.stu.netty.rpcsim.client.discoryProxy.RpcProxy;
import own.stu.netty.rpcsim.registry.zkImpl.Constant;
import own.stu.netty.rpcsim.registry.zkImpl.ZooKeeperServiceDiscovery;
import own.stu.netty.rpcsim.sample.api.HelloService;
import own.stu.netty.rpcsim.sample.api.Person;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloClient {

    public static void main5(String[] args) {

        RpcProxy rpcProxy = new RpcProxy(Constant.ADDRESS);

        int threadNum = 10;
        int loopCount = 100;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final CountDownLatch latch = new CountDownLatch(loopCount);

        try {
            long start = System.currentTimeMillis();

            for (int i = 0; i < loopCount; i++) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        HelloService helloService = rpcProxy.create(HelloService.class);
                        String result = helloService.hello("World");
                        System.out.println(result);
                        latch.countDown();
                    }
                });
            }
            latch.await();

            long time = System.currentTimeMillis() - start;
            System.out.println("thread: " + threadNum);
            System.out.println("loop: " + loopCount);
            System.out.println("time: " + time + "ms");
            System.out.println("tps: " + (double) loopCount / ((double) time / 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            rpcProxy.stop();
        }
    }

    public static void main4(String[] args) {

        ZooKeeperServiceDiscovery discovery = new ZooKeeperServiceDiscovery(Constant.ADDRESS);
        RpcProxy rpcProxy = new RpcProxy(discovery);

        int loopCount = 100;

        long start = System.currentTimeMillis();

        HelloService helloService = rpcProxy.create(HelloService.class);
        for (int i = 0; i < loopCount; i++) {
            String result = helloService.hello("World");
            System.out.println(result);
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("loop: " + loopCount);
        System.out.println("time: " + time + "ms");
        System.out.println("tps: " + (double) loopCount / ((double) time / 1000));
    }

    public static void main3(String[] args) {

        RpcProxy rpcProxy = new RpcProxy(Constant.ADDRESS);

        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello(new Person("Yong", "Huang"));
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {


        RpcProxy rpcProxy = new RpcProxy(Constant.ADDRESS);

        try {
            HelloService helloService = rpcProxy.create(HelloService.class);
            HelloService helloService2 = rpcProxy.create(HelloService.class, "sample.hello2");
            for (int i = 0; i < 100; i++) {
                String result = helloService.hello("World");
                System.out.println(result);

                String result2 = helloService2.hello("世界");
                System.out.println(result2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcProxy.stop();
        }
    }
}