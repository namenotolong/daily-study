package com.huyong.study.algorithm.leetcode.middle;

public class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    static class TrieNode {
        int count;
        TrieNode[] nextNode=new TrieNode[26];
        public TrieNode(){
            count=0;
        }
    }

    public void insert(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.nextNode[c - 'a'] == null) {
                temp.nextNode[c - 'a'] = new TrieNode();
            }
            temp = temp.nextNode[c - 'a'];
            if (i == word.length() - 1) {
                temp.count += 1;
            }
        }
    }

    public boolean search(String word) {
        TrieNode temp = root;
        boolean find = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.nextNode[c - 'a'] == null) {
                return false;
            }
            temp = temp.nextNode[c - 'a'];
            if (i == word.length() - 1 && temp.count > 0) {
                find = true;
            }
        }
        return find;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            temp = temp.nextNode[c - 'a'];
            if (temp == null) {
                return false;
            }
        }
        return true;
    }

}
