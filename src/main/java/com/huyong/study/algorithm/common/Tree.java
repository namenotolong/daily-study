package com.huyong.study.algorithm.common;

import org.jetbrains.annotations.NotNull;

/**
 * @author huyong
 */
public class Tree implements Comparable<Tree> {
     Long value;
     Tree parent;
     Tree left;
     Tree right;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    @Override
    public int compareTo(@NotNull Tree o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "value=" + value +
                '}';
    }
}
