package com.mj.union;

/**
 * Quick Union - 基于rank的优化
 */
public class UnionFind_QU_R extends UnionFind_QU {
    protected int[] ranks;

    public UnionFind_QU_R(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    /**
     * 将v1的根节点嫁接到v2的根节点上
     * 元素少的嫁接到元素多的树上
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2]++;
        }
    }
}
