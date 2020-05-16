package own.stu.code.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaSpaceOOM {

    /**
     * -XX:NewSize=100M -XX:MaxNewSize=100M
     * -XX:InitialHeapSize=200M -XX:MaxHeapSize=200M
     * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=20M  // 大对象 20M
     * -XX:MetaspaceSize=10M
     * -XX:MaxMetaspaceSize=10M
     * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=./
     *
     * @param args
     */
    public static void main(String[] args) {
        long counter = 0;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println(" prepare check .... ");
                        return methodProxy.invokeSuper(o, objects);
                    } else {
                        return methodProxy.invokeSuper(o, objects);
                    }
                }
            });

            Car car = (Car) enhancer.create();
            car.run();

        }
    }

    static class Car {
        public void run() {
            System.out.println("running ..... ");
        }
    }
}
