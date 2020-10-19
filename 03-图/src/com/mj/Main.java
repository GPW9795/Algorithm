package com.mj;

import com.mj.graph.Graph;
import com.mj.graph.ListGraph;

public class Main {

    public static void main(String[] args) {
        test();
    }

    static void test(){
        ListGraph<String, Integer> graph = new ListGraph<>();
        // 无向图
//        graph.addEdge("V0", "V1");
//        graph.addEdge("V1", "V0");
//
//        graph.addEdge("V0", "V2");
//        graph.addEdge("V2", "V0");
//
//        graph.addEdge("V0", "V3");
//        graph.addEdge("V3", "V0");
//
//        graph.addEdge("V2", "V1");
//        graph.addEdge("V1", "V2");
//
//        graph.addEdge("V2", "V3");
//        graph.addEdge("V3", "V2");
//
//        graph.print();

        // 有向图
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        graph.bfs("V1");

//        graph.removeEdge("V0", "V4");
//        graph.removeVertex("V0");
//        graph.print();
    }
}
