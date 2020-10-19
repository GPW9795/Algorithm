package com.mj.graph;

public interface Graph<V, E> {
    int edgeSize();

    int verticesSize();

    void addVertex(V v);

    void removeVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeEdge(V from, V to);

    void bfs(V begin);
}
