package own.stu.algorithms_4th.string;

import org.junit.Test;
import own.stu.algorithms_4th.string.search.SubStringSearch;
import own.stu.algorithms_4th.string.search.TrieST;

public class StringSearchTest {

  @Test
  public void test() {
    TrieST st = new TrieST();
    st.put("m", "100");
    System.out.println(st);


  }

  @Test
  public void test_violence() {
    int index = SubStringSearch.search_one("0987654321", "987");
    System.out.println(index);

    int index2 = SubStringSearch.search_two("0987654321", "987");
    System.out.println(index2);
  }


  @Test
  public void test_dfa() {

    search("ABABAC");
  }

  public int search(String text) {

    // simulate operation of DFA on text
    int n = text.length();
    int m = n;
    int i, j;
    int[][] dfa = new int[256][m];

    for (i = 0, j = 0; i < n && j < m; i++) {
      j = dfa[text.charAt(i)][j];
    }
    if (j == m) {
      return i - m;    // found
    }
    return n;                    // not found
  }
}
