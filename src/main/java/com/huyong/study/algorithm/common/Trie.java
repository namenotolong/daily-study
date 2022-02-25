package com.huyong.study.algorithm.common;

/**
 * @author huyong
 * ac自动机  fail数组 类似于kmp算法  失败跳转到另外节点查找
 */
public class Trie {
    TrieNode root;

    public void insert(String str) {
        if (root == null) {
            root = new TrieNode();
        }
        TrieNode item = root;
        for (int i = 0; i < str.length(); i++) {
            TrieNode[] next = item.next;
            if (next == null) {
                next = new TrieNode[26];
                item.next = next;
            }
            boolean find = false;
            char c = str.charAt(i);
            for (TrieNode trieNode : next) {
                if (trieNode != null && trieNode.value == c) {
                    find = true;
                    break;
                }
            }
            if (find) {
                continue;
            }
            next[c - 'a'] = new TrieNode(c);
            item = next[c - 'a'];
        }
    }

    public boolean contain(String str) {
        TrieNode item = root;
        int dep = 0;
        while (item != null && dep < str.length()) {
            TrieNode[] next = item.next;
            if (next == null) {
                return false;
            }
            char c = str.charAt(dep);
            boolean find = false;
            for (TrieNode trieNode : next) {
                if (trieNode != null && trieNode.value == c) {
                    item = trieNode;
                    ++dep;
                    find = true;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return dep == str.length();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("she");
        trie.insert("he");
        trie.insert("money");
        System.out.println(trie.contain("money"));
    }
}
class TrieNode {
    char value;
    TrieNode[] next;

    public TrieNode() {
    }

    public TrieNode(char value) {
        this.value = value;
    }
}
