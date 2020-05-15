package own.jdk;

import org.junit.Test;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        assertTrue(true);
        System.out.println(0xf0000000);
        System.out.println(0xc0000000);
        System.out.println(0x80000000);
        System.out.println(0x00010000);
        System.out.println(0x0000ffff);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }

    @Test
    public void test() {

        Map<String, String> map = new IdentityHashMap<>();
        String a = "asb";
        map.put(new String("a"), "1");
        map.put(new String("a"), "2");
        map.put(new String("a"), "3");
        map.put(a, "4");

        System.out.println(map.size());
        System.out.println(map.get(a));

        int i = 1_00_0000;

    }

    static class Demo implements Serializable {

        private int i;

        public Demo(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "i=" + i +
                    '}';
        }
    }

    @Test
    public void test2() {

        ReferenceQueue<Demo> rq = new ReferenceQueue<>();
        Demo demo = new Demo(1);
        SoftReference<Demo> reference = new SoftReference<>(demo, rq);

        SoftReference ref = null;
        while ((ref = (SoftReference) rq.poll()) != null) {
            Object o = ref.get();
            System.out.println(o);
        }
    }

    @Test
    public void test3() {
    /*HashMap hashMap = new HashMap();
    hashMap.entrySet().stream().forEach(entry -> {

    });

    hashMap.size();*/


        LinkedHashMap<String, String> c = new LinkedHashMap(5, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 3;
            }
        };

        c.put("1", "one"); // 1
        print(c.entrySet());
        c.put("2", "two"); // 2 1
        print(c.entrySet());
        c.put("3", "three"); // 3 2 1
        print(c.entrySet());
        c.put("4", "four"); // 4 3 2
        print(c.entrySet());
        if (c.get("2") == null)
            throw new Error(); // 2 4 3
        print(c.entrySet());
        c.put("5", "five"); // 5 2 4
        print(c.entrySet());
        c.put("4", "second four"); // 4 5 2
        print(c.entrySet());
        // Verify cache content.
        if (c.size() != 3)
            throw new Error();
        print(c.entrySet());
        if (!c.get("4").equals("second four"))
            throw new Error();
        print(c.entrySet());
        if (!c.get("5").equals("five"))
            throw new Error();
        print(c.entrySet());
        if (!c.get("2").equals("two"))
            throw new Error();
        // List cache content.
        print(c.entrySet());
    }

    private <K, V> void print(Set<Map.Entry<K, V>> entrySet) {
        for (Map.Entry<K, V> e : entrySet)
            System.out.println(e.getKey() + " : " + e.getValue());
        System.out.println("===========");
    }

    @Test
    public void test7() {
        Map<Integer, Integer> map = new HashMap<>(4);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.entrySet().forEach(e -> {
            System.out.print(e.getKey() + ", " + e.getValue() + ", " + e.hashCode());
            System.out.println();
        });
    }
}

