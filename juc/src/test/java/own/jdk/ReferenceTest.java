package own.jdk;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import own.jdk.ThreadLocalTest.ThreadId;
import own.jdk.reference.User;

public class ReferenceTest {

  @Test
  public void test() throws InterruptedException {

    //创建一个引用队列
    ReferenceQueue queue = new ReferenceQueue();

    //创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
    WeakReference reference = new WeakReference(new Object(), queue);
    System.out.println(reference);

    //当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
    System.gc();

    /* ReferenceHandler从pending中取下该元素，并且将该元素放入到queue中，此时Reference状态为ENQUEUED，Reference.queue = ReferenceENQUEUED */
    /* 当从queue里面取出该元素，则变为INACTIVE，Reference.queue = Reference.NULL */
    Reference reference1 = queue.remove();
    System.out.println(reference1);
  }

  @Test
  public void testReference() throws InterruptedException {
//    softReferenceWithEnoughMemory();
//    softReferenceWithNotEnoughMemory(); //-Xmx5M -Xms5M -XX:+PrintGCDetails
    weakReference();
//    phantomReference();
  }

  private void softReferenceWithEnoughMemory() {
    User user = new User("100");
    SoftReference<User> softReference = new SoftReference<>(user);
    System.out.println(softReference.get());

    user = null;
    System.out.println(softReference.get());

    System.gc();
    System.out.println(softReference.get());
  }

  /**
   * -Xmx5M -Xms5M -XX:+PrintGCDetails
   */
  private void softReferenceWithNotEnoughMemory() {

    User user = new User("100");
    SoftReference<User> softReference = new SoftReference<>(user);
    System.out.println(softReference.get());

    user = null;
    System.out.println(softReference.get());

    try {
      byte[] bytes = new byte[10 * 1024 * 1024];
      //System.gc();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println(softReference.get());
    }
  }

  private static void weakReference() throws InterruptedException {
    User user = new User("100");
    ReferenceQueue<User> q = new ReferenceQueue<>();
    WeakReference<User> reference = new WeakReference<>(user, q);

    System.out.println(reference.get());

    user = null;
    System.out.println(reference.get());

    System.gc();
//    TimeUnit.SECONDS.sleep(1);
    System.out.println(reference.get());
  }

  private void phantomReference() throws InterruptedException {
    ReferenceQueue<User> q = new ReferenceQueue<>();
    User user = new User("99");
    PhantomReference<User> reference = new PhantomReference<>(user, q);
    System.out.println(reference.get());
    System.out.println(q.poll());

    user = null;
    System.gc();
    TimeUnit.SECONDS.sleep(1);
    System.out.println(reference.get());
    System.out.println(q.poll());

  }

  @Test
  public void test3WeakHashMap() throws InterruptedException {

    User user = new User("100");
    String key = new String("100");
    WeakHashMap<String, User> hashMap = new WeakHashMap<>();
    hashMap.put(key, user);

    System.out.println(hashMap);
    key = null;
    System.out.println(hashMap);

    System.gc();
    TimeUnit.SECONDS.sleep(1);
    System.out.println(hashMap);
    System.out.println(user);

  }

  @Test
  public void test4ReferenceStructure() throws InterruptedException {
    User user = new User("10");
    ReferenceQueue<User> q = new ReferenceQueue<>();
    WeakReference<User> reference = new WeakReference<>(user, q);
    System.out.println("-" + q.poll());
    if (!reference.isEnqueued()) {
      reference.enqueue();
    }

    User user2 = new User("20");
    WeakReference<User> reference2 = new WeakReference<>(user2, q);
    if (!reference2.isEnqueued()) {
      reference2.enqueue();
    }

    User user3 = new User("30");
    WeakReference<User> reference3 = new WeakReference<>(user3, q);
    if (!reference3.isEnqueued()) {
      reference3.enqueue();
    }

    /*Reference<User> ref = null;
    while ((ref = (Reference<User>) q.poll()) != null) {
      System.out.println(ref.get());
    }*/

    user = null;
    user2 = null;
    user3 = null;
    System.gc();
    TimeUnit.SECONDS.sleep(1);
    Reference<User> ref = null;
    while ((ref = (Reference<User>) q.poll()) != null) {
      System.out.println(ref.get());
    }

  }

  @Test
  public void test5() {
    HashMap map = new HashMap();
    TestObject t1 = new TestObject();
    TestObject t2 = new TestObject();
    map.put(t1, "1");
    map.put(t2, "2");
    t1 = null;
    System.gc();
    System.out.println("第1步:" + map);

    t2 = null;
//    map.remove(t1);
    System.gc();
    System.out.println("第2步:" + map);

    map.clear();
    System.gc();
    System.out.println("第3步:" + map);
  }

  static class TestObject {

    private String strTest = "该Test对象还存在";

    @Override
    public String toString() {
      return strTest;
    }

    @Override
    protected void finalize() throws Throwable {
      System.out.println("该Test对象被释放了");
    }

  }
}
