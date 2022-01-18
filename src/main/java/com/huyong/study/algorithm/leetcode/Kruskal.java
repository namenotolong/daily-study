package com.huyong.study.algorithm.leetcode;

import com.google.common.collect.Lists;
import com.huyong.study.algorithm.common.Edge;

import java.util.*;

/**
 * @author huyong
 * kruskal 求最小生成树 每次都选最小的可选边，起环抛弃，直到n-1个边
 * prim 加点法，在未加入的点中选择权值最小的加入，直到n-1个边
 * Dijkstra 求最短路 求一个图中一个点到其他所有点的最短路径的算法，时间复杂度 O(n2)
 */
public class Kruskal {

    public List<Edge<String>> mockEdge() {
        List<Edge<String>> result = Lists.newArrayList();

        Edge<String> ab = new Edge<>();
        ab.setStart("A");
        ab.setEnd("B");
        ab.setWeight(12);
        result.add(ab);

        Edge<String> AG = new Edge<>();
        AG.setStart("A");
        AG.setEnd("G");
        AG.setWeight(14);
        result.add(AG);

        Edge<String> AF = new Edge<>();
        AF.setStart("A");
        AF.setEnd("F");
        AF.setWeight(16);
        result.add(AF);

        Edge<String> BF = new Edge<>();
        BF.setStart("B");
        BF.setEnd("F");
        BF.setWeight(7);
        result.add(BF);

        Edge<String> BC = new Edge<>();
        BC.setStart("B");
        BC.setEnd("C");
        BC.setWeight(10);
        result.add(BC);

        Edge<String> GF = new Edge<>();
        GF.setStart("G");
        GF.setEnd("F");
        GF.setWeight(9);
        result.add(GF);

        Edge<String> GE = new Edge<>();
        GE.setStart("G");
        GE.setEnd("E");
        GE.setWeight(8);
        result.add(GE);

        Edge<String> EF = new Edge<>();
        EF.setStart("E");
        EF.setEnd("F");
        EF.setWeight(2);
        result.add(EF);

        Edge<String> ED = new Edge<>();
        ED.setStart("E");
        ED.setEnd("D");
        ED.setWeight(4);
        result.add(ED);

        Edge<String> EC = new Edge<>();
        EC.setStart("E");
        EC.setEnd("C");
        EC.setWeight(5);
        result.add(EC);

        Edge<String> CF = new Edge<>();
        CF.setStart("C");
        CF.setEnd("F");
        CF.setWeight(6);
        result.add(CF);

        Edge<String> CD = new Edge<>();
        CD.setStart("C");
        CD.setEnd("D");
        CD.setWeight(3);
        result.add(CD);
        return result;
    }

    public static int[][] mockArrGraph() {
        return new int[][]{
                {0, 2, -1, 6}
                , {2, 0, 3, 2}
                , {-1, 3, 0, 2}
                , {6, 2, 2, 0}};
    }

    private void sort(List<Edge<String>> chat, int start, int end) {
        int template = chat.get(start).getWeight();
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && chat.get(j).getWeight() > template) {
                --j;
            }
            while (i < j && chat.get(i).getWeight() < template) {
                ++i;
            }
            Edge<String> temp = chat.get(i);
            chat.set(i, chat.get(j));
            chat.set(j, temp);
        }
        if (i > start) {
            sort(chat, start, i);
        }
        if (end > j) {
            sort(chat, j + 1, end);
        }
    }

    Map<String, String> parentMap = new HashMap<>();

    public String find(String value) {
        String result = parentMap.get(value);
        if (!Objects.equals(result, value)) {
            result = find(result);
            parentMap.put(value, result);
        }
        return result;
    }

    public void unit(String child, String parent) {
        parentMap.put(child, parent);
    }

    public List<Edge<String>> generateMinTree(List<Edge<String>> list) {
        //权重排序
        sort(list, 0, list.size() - 1);
        Set<String> exists = new HashSet<>();
        Set<String> allVertex = new HashSet<>();
        for (Edge<String> item : list) {
            allVertex.add(item.getEnd());
            allVertex.add(item.getStart());
            parentMap.put(item.getStart(), item.getStart());
            parentMap.put(item.getEnd(), item.getEnd());
        }
        List<Edge<String>> result = new ArrayList<>();
        while (exists.size() < allVertex.size()) {
            for (Edge<String> item : list) {

                //成环
                String start = find(item.getStart());
                String end = find(item.getEnd());
                if (start != null && end != null && Objects.equals(start, end)) {
                    continue;
                }

                exists.add(item.getStart());
                exists.add(item.getEnd());
                result.add(item);

                //并
                unit(start, end);
            }
        }
        return result;
    }

    public static Map<Integer, Integer> dijkstra(int[][] graph, int startVertex) {
        Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> rest = new HashMap<>();
        for (int i = 0; i < graph[startVertex].length; i++) {
            rest.put(i, graph[startVertex][i]);
        }
        while (!rest.isEmpty()) {
            //找出当前最小值
            int min = Integer.MAX_VALUE;
            boolean find = false;
            for (Map.Entry<Integer, Integer> item : rest.entrySet()) {
                if (item.getValue() >= 0 && item.getValue() < min) {
                    min = item.getKey();
                    find = true;
                }
            }
            if (!find) {
                throw new RuntimeException("路径存在不可达");
            }
            Integer curValue = rest.get(min);
            result.put(min, curValue);
            for (int i = 0; i < graph[min].length; i++) {
                if (graph[min][i] >= 0 && i != min) {
                    Integer value = rest.get(i);
                    if (value != null) {
                        int updateValue = graph[min][i] + curValue;
                        if (value < 0 || updateValue < value) {
                            //更新最小值
                            rest.put(i, updateValue);
                        }
                    }
                }
            }
            rest.remove(min);
        }
        return result;
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        List<Edge<String>> edges = kruskal.mockEdge();
        List<Edge<String>> minTree = kruskal.generateMinTree(edges);
        for (Edge<String> stringEdge : minTree) {
            System.out.println(stringEdge);
        }
        System.out.println(dijkstra(mockArrGraph(), 0));
    }
}
