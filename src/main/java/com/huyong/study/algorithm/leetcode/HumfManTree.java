package com.huyong.study.algorithm.leetcode;

import com.google.common.collect.Lists;
import com.huyong.study.algorithm.common.Tree;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huyong
 */
public class HumfManTree {

    private static List<Tree> mockTree() {
        //271
        List<Integer> list = Lists.newArrayList(5,29,7,8,14,23,3,11);
        return list.stream().map(e -> {
            Tree item = new Tree();
            item.setValue(Long.valueOf(e));
            return item;
        }).collect(Collectors.toList());
    }

    public static long sumTree(Tree root) {
        return sumTreeMiddle(root, 0);
    }

    private static long sumTreeMiddle(Tree root, int depth) {
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.printf("%d * %d = %d\n", root.getValue(), depth, root.getValue() * (depth));
            return root.getValue() * (depth);
        }
        if (root.getLeft() == null) {
            return sumTreeMiddle(root.getRight(), depth + 1);
        }
        return sumTreeMiddle(root.getRight(), depth + 1) +
                sumTreeMiddle(root.getLeft(), depth + 1);
    }

    public static Tree humf(List<Tree> list) {
        Collections.sort(list);
        while (!list.isEmpty()) {
            if (list.size() == 1) {
                return list.get(0);
            }
            Tree one = list.get(0);
            Tree second = list.get(1);
            Tree parent = new Tree();
            parent.setValue(one.getValue() + second.getValue());
            if (one.getValue() > second.getValue()) {
                parent.setRight(one);
                parent.setLeft(second);
            } else {
                parent.setLeft(one);
                parent.setRight(second);
            }
            one.setParent(parent);
            second.setParent(parent);
            list.remove(0);
            list.set(0, parent);
            Collections.sort(list);
        }
        return null;
    }

    public static void main(String[] args) {
        Tree root = humf(mockTree());
        System.out.println(root);
        assert root != null;
        System.out.println(root.getLeft());
        System.out.println(root.getRight());
        System.out.println(sumTree(root));
    }

}
