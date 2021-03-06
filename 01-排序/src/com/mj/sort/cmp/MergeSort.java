package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin,end)进行切割
     */
    private void sort(int begin, int end) {
        // 至少要有两个元素
        if (end - begin < 2) return;

        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将[begin, mid)和[mid,emd)合并程有序序列
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        // 左边还未结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
