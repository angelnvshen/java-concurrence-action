package own.leetcode.complexStruct.triesTree;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static own.leetcode.complexStruct.triesTree.TrieTree.TriesNode.TRIES_MAX_NODE_NUM;

@Data
public class TrieTree {

    private List<TriesNode> nodeList = new ArrayList<>();
    private TriesNode root;

    public TrieTree() {
        root = new TriesNode();
        Iterator<TriesNode> iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        TriesNode tmp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (tmp.child[c - 'a'] == null) {
                tmp.child[c - 'a'] = new TriesNode();
            }
            tmp = tmp.child[c - 'a'];
        }
        tmp.end = true;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TriesNode tmp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (tmp.child[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.child[c - 'a'];
        }
        return tmp.end;
    }

    public boolean startWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        TriesNode tmp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (tmp.child[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.child[c - 'a'];
        }
        return true;
    }

    public static void main_t(String[] args) {
        TriesNode node = new TriesNode();
        System.out.println(node.end);
        for (int i = 0; i < TRIES_MAX_NODE_NUM; i++) {
            System.out.println(node.child[i]);
        }
    }

    public static void main2(String[] args) {
        TriesNode root = new TriesNode();
        TriesNode n1 = new TriesNode();
        TriesNode n2 = new TriesNode(true);
        TriesNode n3 = new TriesNode();
        root.child['a' - 'a'] = n1;
        root.child['b' - 'a'] = n2;
        root.child['e' - 'a'] = n3;

        TriesNode n4 = new TriesNode();
        TriesNode n5 = new TriesNode();
        TriesNode n6 = new TriesNode();
        n1.child['b' - 'a'] = n4;
        n2.child['c' - 'a'] = n5;
        n3.child['f' - 'a'] = n6;

        TriesNode n7 = new TriesNode(true);
        TriesNode n8 = new TriesNode(true);
        TriesNode n9 = new TriesNode(true);
        TriesNode n10 = new TriesNode(true);

        n4.child['c' - 'a'] = n7;
        n4.child['d' - 'a'] = n8;
        n5.child['d' - 'a'] = n9;
        n6.child['g' - 'a'] = n10;

        TriesNode n11 = new TriesNode(true);
        n7.child['d' - 'a'] = n11;

        preOrder(root, 0);

        List<Character> chars = new ArrayList<>();
        List<String> words = new ArrayList<>();
        getAllWord(root, chars, words);
        System.out.println(words);

    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        List<String> words = Lists.newArrayList("abc", "abcd", "abd", "b", "bcd", "efg");
        for (String word : words) {
            trieTree.insert(word);
        }
        /*preOrder(trieTree.root, 0);
        for (String word : words) {
            System.out.println(trieTree.search(word));
        }*/

        WordDictionary dictionary = new WordDictionary(trieTree);

        List<String> searchWord = Lists.newArrayList("a","a.","a.c","a.cd", "a.c.","a.ce","a...");
        for(String word :searchWord){
            System.out.println(dictionary.search(word));
        }
    }

    private static void preOrder(TriesNode root, int layer) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < TRIES_MAX_NODE_NUM; i++) {

            if (root.child[i] != null) {
                for (int j = 0; j < layer; j++) {
                    System.out.print("----");
                }
                System.out.print((char) ('a' + i));
                if (root.child[i].end) {
                    System.out.print("(end)");
                }
                System.out.println();
                preOrder(root.child[i], layer + 1);
            }
        }

    }

    public static void getAllWord(TriesNode root, List<Character> chars, List<String> words) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < TRIES_MAX_NODE_NUM; i++) {
            if (root.child[i] != null) {
                chars.add((char) (i + 'a'));

                if (root.child[i].end) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = 0; j < chars.size(); j++) {
                        sb.append(chars.get(j));
                    }
                    words.add(sb.toString());
                }
                getAllWord(root.child[i], chars, words);
                chars.remove(chars.size() - 1);
            }
        }

    }

    @Data
    public static class TriesNode {
        public static int TRIES_MAX_NODE_NUM = 26;
        private TriesNode[] child;
        boolean end;

        public TriesNode() {
            this.end = false;
            child = new TriesNode[TRIES_MAX_NODE_NUM];
        }

        public TriesNode(boolean end) {
            this.end = end;
            child = new TriesNode[TRIES_MAX_NODE_NUM];
        }

        public TriesNode[] getChild() {
            return child;
        }

        public void setChild(TriesNode[] child) {
            this.child = child;
        }
    }
}
