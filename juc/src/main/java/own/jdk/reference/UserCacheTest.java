package own.jdk.reference;

public class UserCacheTest {

  public static void main(String[] args) {
    UserCache cache = UserCache.getInstance();

    for (int i = 0; i < 2; i++) {
      System.out.println(cache.getUser("123").toString());
    }
    cache.clearAll();
    System.out.println(cache.getUser("123").toString());
  }

}