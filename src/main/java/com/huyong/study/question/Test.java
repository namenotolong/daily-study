package com.huyong.study.question;

import com.google.common.collect.Comparators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-16 8:56 下午
 */
public class Test {

    private static class Node{
        private int index;
        private char value;

        public Node(final int index, final char value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(final int index) {
            this.index = index;
        }

        public char getValue() {
            return value;
        }

        public void setValue(final char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }
    }

    public static void test() {
        String str = "abcdasdedafafdsdfsadfasdfsadfasdfasdfa";
        List<Node> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            list.add(new Node(i, chars[i]));
        }
        Map<Character, List<Node>> charGroup = list.stream().collect(Collectors.groupingBy(Node::getValue));
        Map<Character, List<Node>> standardCopy = new HashMap<>();
        charGroup.forEach((c, nodes) -> {
            if (nodes.size() > 1) {
                standardCopy.put(c, nodes);

            }
        });
        Stream<Node> nodeStream = standardCopy.values().stream().flatMap(Collection::stream);
        List<Node> collect = nodeStream.sorted((e1, e2) -> e1.getIndex() > e2.getIndex() ? 0 : -1).collect(Collectors.toList());
        String collect1 = collect.stream().map(e -> e.getValue() + "").collect(Collectors.joining());
        System.out.println(collect1);

        System.out.println(collect);
    }

    public static void main(String[] args) {
        test();
    }
}
