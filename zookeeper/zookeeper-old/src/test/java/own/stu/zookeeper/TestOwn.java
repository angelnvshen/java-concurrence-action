package own.stu.zookeeper;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class TestOwn {

    @Test
    public void teset() throws UnknownHostException {
//        String host = "www.baidu.com";
        String host = "www.google.com";
        InetAddress[] addresses = InetAddress.getAllByName(host);
        for(InetAddress address : addresses){
            System.out.println(address.getHostName() + " " + address.getHostAddress());
        }
    }

    @Test
    public void initNextSessionIdTest(){
        Long num = System.currentTimeMillis();
        System.out.println(num);
        System.out.println(Long.toBinaryString(num) + " -- " + Long.toBinaryString(num).length());
        num = num << 24;
        System.out.println(Long.toBinaryString(num) + " -- " + Long.toBinaryString(num).length());
    }

}
