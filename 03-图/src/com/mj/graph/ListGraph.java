package com.mj.graph;

import com.mj.MinHeap;
import com.mj.UnionFind;

import java.util.*;
import java.util.Map.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    /**
     * 顶点
     */
    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        Vertex(V value) {
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

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
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

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return Math.random() > 0.5 ? prim() : kruskal(); // 随机选择
    }

    /**
     * 最小生成树 - Prim算法
     */
    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertices.values().iterator(); // 迭代器
        if (!it.hasNext()) return null; // 图为空

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        Vertex<V, E> vertex = it.next(); // 随机拿到一个顶点
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        int verticesSize = vertices.size();
        while (!heap.isEmpty() && addedVertices.size() < verticesSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) continue;

            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    /**
     * 最小生成树 - Kruskal算法
     */
    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1; // 最终边的数量
        if (edgeSize == -1) return null;

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>(); // 放入所有的边
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator); // 初始化并查集
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (uf.isSame(edge.from, edge.to)) continue;

            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }
        return edgeInfos;
    }

    /**
     * 拓扑排序
     */
    @Override
    public List<V> topologicalSort() {
        // 最终拓扑排序的结果
        List<V> list = new ArrayList<>();
        // 度为0的队列
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 存放入度的映射
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        // 初始化，将度为0的顶点放入队列
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            Integer in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, in);
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            // 放入返回结果
            list.add(vertex.value);

            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            }
        }

        return list;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    /**
     * Dijkstra算法
     *
     * @param begin 起点
     * @return 最短路径集合
     */
    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>(); // 存放最短路径值
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>(); // 中间值，每次挑选最小的
        // 初始化paths
        for (Edge<V, E> edge : beginVertex.outEdges) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }

        while (!paths.isEmpty()) {
            // minEntry离开桌面
            Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);
            // 对minVertex的outEdges进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                // 如果已经确定最短路径就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relaxForDijkstra(edge, minPath, paths);
            }
        }
        // 删除起点
        selectedPaths.remove(begin);
        return selectedPaths;
    }

    /**
     * 松弛操作 - Dijkstra算法
     *
     * @param edge     需要被松弛的边
     * @param fromPath edge.from的路径信息
     * @param paths    存放其他点的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 新的最短路径beginVertex到edge.from到edge.to的最短路径
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 以前的beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 从paths中挑选最小路径(顶点和权值）- Dijkstra算法
     */
    private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
        Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
        while (it.hasNext()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    /**
     * Bellman-Ford算法
     *
     * @param begin 起点
     * @return 最短路径集合
     */
    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>(); // 存放最短路径值
        // 初始化起点的paths
        PathInfo<V, E> beginPath = new PathInfo<>();
        beginPath.weight = weightManager.zero();
        selectedPaths.put(begin, beginPath);

        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) { // V - 1次
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) continue; // 起点不存在最短路径信息
                relaxForBellmanFord(edge, fromPath, selectedPaths);
            }
        }

        // 检测负权环
        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            if (fromPath == null) continue; // 起点不存在最短路径信息
            if (relaxForBellmanFord(edge, fromPath, selectedPaths)) {
                System.out.println("有负权环");
                return null;
            }
        }

        selectedPaths.remove(begin);
        return selectedPaths;
    }

    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        // 新的最短路径beginVertex到edge.from到edge.to的最短路径
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        // 以前的beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
        return true;
    }
}
