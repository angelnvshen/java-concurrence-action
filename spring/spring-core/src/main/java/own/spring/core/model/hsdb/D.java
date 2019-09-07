package own.spring.core.model.hsdb;

public class D {

  public int inc() {
    int x;
    try {
      x = 1;
      return x;
    } catch (Exception e) {
      x = 2;
      return x;
    } finally {
      x = 3;
    }
  }
}
