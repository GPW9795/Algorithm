package com.mj.sort;

public class BubbleSort3<E extends Comparable<E>> extends Sort<E>{
    /**
     * 冒泡排序优化2
     * 记录最后一次交换的位置作为下一次的截止位置
     */
    @Override
    protected void sort() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            // 其初始值在数组完全有序时有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    // 记录一下最后一次交换位置
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

}
