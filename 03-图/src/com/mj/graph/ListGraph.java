package com.mj.graph;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    /**
     * 顶点
     */
    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return Objects.equals(value, ((Vertex<V, E>) o).value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? null : value.toString();
        }
    }

    /**
     * 边
     */
    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public void print() {
        System.out.println("【顶点】---------------------------");
        vertices.forEach((V key, Vertex<V, E> vertex) -> {
            System.out.println(vertex);
//            System.out.println("out-----------------------");
//            System.out.println(vertex.outEdges);
//            System.out.println("in------------------------");
//            System.out.println(vertex.inEdges);
        });

        System.out.println("【边】------------------------------");
        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void removeVertex(V v) {
        // 获取并删除顶点
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        // 删除从该节点出去的边
        Iterator iteratorOut = vertex.outEdges.iterator();
        while (iteratorOut.hasNext()) {
            Edge<V, E> edge = (Edge<V, E>) iteratorOut.next();
            edge.to.inEdges.remove(edge);
            iteratorOut.remove(); // 将当前遍历的元素从集合中删掉
            edges.remove(edge);
        }
        // 删除从该节点进去的边
        Iterator iteratorIn = vertex.inEdges.iterator();
        while (iteratorIn.hasNext()) {
            Edge<V, E> edge = (Edge<V, E>) iteratorIn.next();
            edge.from.outEdges.remove(edge);
            iteratorIn.remove(); // 将当前遍历的元素从集合中删掉
            edges.remove(edge);
        }
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        // 判断顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        // 删除原来的边
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        // 添加新的边
        edges.add(edge);
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
    }

    @Override
    public void removeEdge(V from, V to) {
        // 判断顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        // 删除原来的边
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        // 判断顶点是否存在
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        // 已遍历过的集合
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 添加顶点
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        // 开始遍历
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) return;
            // 添加连接顶点
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    /**
     * 深度优先搜索的递归实现
     */
    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) return;
        // 判断顶点是否存在
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
//        dfs(beginVertex, new HashSet<>(), visitor);
        dfs(beginVertex, visitor);
    }

    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices, VertexVisitor<V> visitor) {
        if (visitor.visit(vertex.value)) return;
        visitedVertices.add(vertex);

        for (Edge<V, E> edge : vertex.outEdges) {
            if (visitedVertices.contains(edge.to)) continue;
            dfs(edge.to, visitedVertices, visitor);
        }
    }

    /**
     * 深度优先搜索的非递归实现
     */
    private void dfs(Vertex<V, E> beginVertex, VertexVisitor<V> visitor) {
        Stack<Vertex<V, E>> stack = new Stack<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        // 先访问起点
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        if (visitor.visit(beginVertex.value)) return;

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                // 选择一条没有访问过的边
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                if (visitor.visit(edge.to.value)) return;
                break;
            }
        }
    }
}
