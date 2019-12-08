package own.stu.learn.withSpringBoot;

import org.apache.tomcat.websocket.server.UpgradeUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OwnTest {

    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<UpgradeUtil> upgradeUtilClass = UpgradeUtil.class;
        //Method[] methods = upgradeUtilClass.getMethods();
        Method[] methods = upgradeUtilClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println(" ======== ");
        Method getWebSocketAccept = UpgradeUtil.class.getDeclaredMethod("getWebSocketAccept", String.class);
        getWebSocketAccept.setAccessible(true);
        Object invoke = getWebSocketAccept.invoke(null, "hj0eNqbhE/A0GkBXDRrYYw==");
        System.out.println(invoke);
    }

    @Test
    public void test2() {

        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}
