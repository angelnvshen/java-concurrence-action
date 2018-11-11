package own.stu.sourcecore.junit;

import junit.framework.ComparisonFailure;

public class PureTest {

  public static void main(String[] args) {
    ComparisonFailure comparisonFailure = new ComparisonFailure("xxx", "1234", "134");
    System.out.println(comparisonFailure.getMessage());
  }
}
