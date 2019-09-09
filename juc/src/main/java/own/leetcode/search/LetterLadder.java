package own.leetcode.search;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import javafx.util.Pair;

public class LetterLadder {

  /**
   * 两个单词如果有一个字符不同，则表示可以相连   e:(hot lot)
   */
  static boolean connect(String word1, String word2) {
    int dif_char_num = 0;
    for (int i = 0; i < word1.length(); i++) {
      if (word1.charAt(i) != word2.charAt(i)) {
        dif_char_num++;
      }
    }
    return dif_char_num == 1;
  }

  static void construct_grap(String beginWord, List<String> wordList, Map<String, List<String>> graph) {

    wordList.add(beginWord);

    for (String word : wordList) {
      graph.put(word, Lists.newArrayList());
    }

    for (int i = 0; i < wordList.size(); i++) {
      for (int j = i + 1; j < wordList.size(); j++) {
        String word1 = wordList.get(i);
        String word2 = wordList.get(j);
        if (connect(word1, word2)) {
          graph.get(word1).add(word2);
          graph.get(word2).add(word1);
        }
      }
    }
  }

  /**
   * BFS
   */
  static int findShortestPath(String beginWord, String endWord, List<String> wordList) {

    Map<String, List<String>> graph = new HashMap<>();
    construct_grap(beginWord, wordList, graph);

    Set<String> visited = new HashSet<>();
    Queue<Pair<String, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(beginWord, 1));
    while (!queue.isEmpty()) {

      Pair<String, Integer> pair = queue.remove();

      if (Objects.equals(endWord, pair.getKey())) {
        return pair.getValue();
      }

      visited.add(pair.getKey());

      List<String> strings = graph.get(pair.getKey());
      for (String s : strings) {
        if (!visited.contains(s)) {
          queue.add(new Pair<>(s, pair.getValue() + 1));
          visited.add(s);
        }
      }
    }

    return 0;
  }

  public static void main(String[] args) {

    List<String> list = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println(findShortestPath("hit", "cog", list));
  }
}
