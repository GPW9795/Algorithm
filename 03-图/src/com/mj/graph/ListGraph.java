package com.mj.graph;

import java.util.HashSet;
import java.util.Set;

public class ListGraph<V,E> implements Graph<V,E> {

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int verticesSize() {
        return 0;
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to) {

    }

    @Override
    public void addEdge(V from, V to, E weight) {

    }

    @Override
    public void removeEdge(V from, V to) {

    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V,E>> inEdges = new HashSet<>();
        Set<Edge<V,E>> outEdges = new HashSet<>();
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;
    }
}
