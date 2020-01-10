package own.stu.code;

import org.junit.Test;

import java.util.Hashtable;

public class OwnTest {
    @Test
    public void test(){

//        boolean interrupted = false;
//        interrupted |= false;
//        System.out.println(interrupted);
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null, "HELLO");
        System.out.println(hashtable);
    }
}


