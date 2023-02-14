package com.huyong.study.algorithm.leetcode.middle;

import java.util.Stack;

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> ref;

    public MinStack() {
        stack = new Stack<>();
        ref = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (ref.size() > 0) {
            ref.push(Math.min(val, ref.peek()));
        } else {
            ref.push(val);
        }
    }

    public void pop() {
        if (stack.size() > 0) {
            stack.pop();
            ref.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return ref.peek();
    }
}