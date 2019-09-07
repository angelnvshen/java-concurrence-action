package own.jdk.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;
import java.util.Random;

public class UserCache {

  private static volatile UserCache cache;
  private Hashtable<String, UserRef> userRefs;
  private ReferenceQueue<User> q;

  private class UserRef extends SoftReference<User> {

    private String key = "";

    public UserRef(User user, ReferenceQueue q) {
      super(user, q);
      key = user.getId();
    }
  }

  private UserCache() {
    userRefs = new Hashtable();
    q = new ReferenceQueue<>();
  }

  public static UserCache getInstance() {
    synchronized (UserCache.class) {
      if (cache == null) {
        synchronized (UserCache.class) {
          cache = new UserCache();
        }
      }
    }

    return cache;
  }

  /**
   * 缓存用户数据
   */
  private void cacheUser(User user) {
//    cleanCache();
    UserRef ref = new UserRef(user, q);
    userRefs.put(user.getId(), ref);
  }

  /**
   * 获取用户数据
   */
  public User getUser(String id) {
    User user = null;
    if (userRefs.containsKey(id)) {
      System.out.println("get data from cache");
      UserRef ref = userRefs.get(id);
      user = ref.get();
    }
    if (user == null) {
      System.out.println("get data from db");
      user = new User(id);
      user.setName("dong" + new Random().nextInt(10));
      cacheUser(user);
    }
    return user;
  }

  private void cleanCache() {
    UserRef ref = null;
    while ((ref = (UserRef) q.poll()) != null) {
      userRefs.remove(ref.key);
    }
  }

  public void clearAll() {
    cleanCache();
    userRefs.clear();
    System.gc();
    System.runFinalization();
  }

}
