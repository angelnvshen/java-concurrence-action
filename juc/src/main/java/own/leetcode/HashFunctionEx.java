package own.leetcode;

import java.util.ArrayList;
import java.util.List;

public class HashFunctionEx {


  public static boolean is_window_ok(int[] map_s, int[] map_t, List<Character> list) {

    for (int i = 0; i < list.size(); i++) {
      if (map_s[list.get(i)] < map_t[list.get(i)]) {
        return false;
      }
    }

    return true;
  }

  public static String minWindow(String s, String t) {

    int max_len = 128;
    int[] map_s = new int[max_len];
    int[] map_t = new int[max_len];

    List<Character> list = new ArrayList<>();

    for (int i = 0; i < t.length(); i++) {
      map_t[t.charAt(i)]++;
    }

    for (int i = 0; i < max_len; i++) {
      if (map_t[i] > 0) {
        list.add((char) i);
      }
    }

    int window_begin = 0;
    String result = "";

    for (int i = 0; i < s.length(); i++) {
      map_s[s.charAt(i)]++;
      while (window_begin < i) {
        char begin_ch = s.charAt(window_begin);
        if (map_t[begin_ch] == 0) {
          window_begin++;
        } else if (map_s[begin_ch] > map_t[begin_ch]) {
          map_s[begin_ch]--;
          window_begin++;
        } else {
          break;
        }
      }
      if (is_window_ok(map_s, map_t, list)) {
        int new_windown_len = i - window_begin + 1;
        if (result == "" || result.length() > new_windown_len) {
          result = s.substring(window_begin, i + 1);
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    System.out.println(minWindow("MADOBCCABEC", "ABCC"));
    System.out.println(minWindow("aa", "aa"));
  }

}
