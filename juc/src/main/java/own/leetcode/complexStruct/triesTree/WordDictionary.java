package own.leetcode.complexStruct.triesTree;

import static own.leetcode.complexStruct.triesTree.TrieTree.TriesNode.TRIES_MAX_NODE_NUM;

public class WordDictionary {
    private TrieTree trieTree;

    public WordDictionary(TrieTree trieTree) {
        this.trieTree = trieTree;
    }

    public void insert(String word) {
        trieTree.insert(word);
    }

    public boolean search(String word) {
        return search(trieTree.getRoot(), word);
    }

    private boolean search(TrieTree.TriesNode node, String word) {

        if (word == null || word.length() == 0) {
            return node.end;
        }

        char c = word.charAt(0);

        if (c == '.') {
            for (int i = 0; i < TRIES_MAX_NODE_NUM; i++) {
                if (node.getChild()[i] != null &&
                        search(node.getChild()[i], word.substring(1))) {
                    return true;
                }
            }
        } else {
            if (node.getChild()[c - 'a'] != null &&
                    search(node.getChild()[c - 'a'], word.substring(1))) {
                return true;
            }
        }

        return false;
    }

}
