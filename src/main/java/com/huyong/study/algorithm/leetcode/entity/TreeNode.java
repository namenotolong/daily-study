package com.huyong.study.algorithm.leetcode.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
}
class Main{

    //镜像二叉树
    public TreeNode mirrorTree(TreeNode root) {
        if (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.right = left;
            root.left = right;
            mirrorTree(left);
            mirrorTree(right);
        }
        return root;
    }

    //98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Integer[] buff = new Integer[2];
        isValidBST(root, buff);
        return buff[1] == null;
    }

    public void isValidBST(TreeNode root, Integer[] buff) {
        if (root == null) {
            return;
        }
        isValidBST(root.left, buff);
        if (buff[0] != null && buff[0] >= root.val) {
            buff[1] = root.val;
            return;
        } else {
            buff[0] = root.val;
        }
        isValidBST(root.right, buff);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode right = new TreeNode(1);
        treeNode.left = right;
        boolean validBST = new Main().isValidBST(treeNode);
        System.out.println(validBST);
    }
}
