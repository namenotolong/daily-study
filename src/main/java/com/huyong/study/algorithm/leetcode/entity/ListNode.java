package com.huyong.study.algorithm.leetcode.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public void print(ListNode root) {
        ListNode temp = root;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 两个一批  记录上一次的第二个作为父节点
     */
    public static ListNode reverseList(ListNode root) {
        ListNode temp = root;
        ListNode last = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = last;
            if (next != null) {
                ListNode nextItem = next.next;
                next.next = temp;
                temp = nextItem;
                last = next;
            } else {
                return temp;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        List<ListNode> list = IntStream.range(0, 10)
                .mapToObj(ListNode::new)
                .collect(Collectors.toList());
        ListNode root = null;
        ListNode parent = null;
        for (ListNode listNode : list) {
            if (root == null) {
                root = listNode;
                parent = listNode;
            }
            if (parent != null) {
                parent.next = listNode;
                parent = listNode;
            }
        }
        root.print(root);
        ListNode listNode = reverseList(root);
        listNode.print(listNode);
    }
}
