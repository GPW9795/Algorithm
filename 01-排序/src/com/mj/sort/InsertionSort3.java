package com.mj.sort;

import com.mj.BinarySearch;

public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            insert(i, search(i));
        }
    }

    /**
     * 插入元素
     */
    private void insert(int source, int dest) {
        E v = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;
    }

    /**
     * 查找index位置元素的待插入位置
     * 已经排好序的数组范围为[0，index）
     */
    private int search(int index) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) >= 0) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }
}
