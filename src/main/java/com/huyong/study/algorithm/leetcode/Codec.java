package com.huyong.study.algorithm.leetcode;

import com.huyong.study.algorithm.leetcode.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//297
public class Codec {

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            while (count < size) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    sb.append("n");
                } else {
                    queue.add(poll.left);
                    queue.add(poll.right);
                    sb.append(poll.val);
                }
                ++count;
                if (count != size) {
                    sb.append(",");
                }
            }
            sb.append("m");
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data.trim())) {
            return null;
        }
        String[] arr = data.split("m");
        TreeNode result = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(result);
        for (int i = 1; i < arr.length; i++) {
            String[] items = arr[i].split(",");
            for (int j = 0; j < items.length; j += 2) {
                TreeNode poll = queue.poll();
                TreeNode left = null;
                TreeNode right = null;
                if (!"n".equals(items[j])) {
                    left = new TreeNode(Integer.parseInt(items[j]));
                }
                if (!"n".equals(items[j + 1])) {
                    right = new TreeNode(Integer.parseInt(items[j + 1]));
                }
                poll.left = left;
                poll.right = right;
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
        }
        return result;
    }
}
