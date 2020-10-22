package com.mj;

import com.mj.graph.Graph;
import com.mj.graph.Graph.*;
import com.mj.graph.ListGraph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    static WeightManager<Double> weightManager = new WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    public static void main(String[] args) {
        testMultiShortestPath();
    }

    static void testMultiShortestPath() {
        Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
        Map<Object, Map<Object, PathInfo<Object, Double>>> map = graph.shortestPath();
        if (map == null) return;
        map.forEach((Object from, Map<Object, PathInfo<Object, Double>> paths) -> {
            System.out.println(from + "----------------------------------------");
            paths.forEach((Object to, PathInfo<Object, Double> path) -> {
                System.out.println(to + "-" + path);
            });
        });
    }

    static void testShortestPath() {
        Graph<Object, Double> graph = directedGraph(Data.SP);
        Map<Object, PathInfo<Object, Double>> map = graph.shortestPath("A");
        if (map == null) return;
        map.forEach((Object o, PathInfo<Object, Double> path) -> {
            System.out.println(o + "-" + path);
        });
    }

    static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
        Set<EdgeInfo<Object, Double>> infos = graph.mst();
        for (EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void testTopo() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    static void testDfs() {
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("a", (Object o) -> {
            System.out.println(o);
            return false;
        });
    }

    static void testBfs() {
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0, (Object o) -> {
            System.out.println(o);
            return false;
        });
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        ListGraph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        ListGraph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
