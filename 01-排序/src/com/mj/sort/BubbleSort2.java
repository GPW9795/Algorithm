package com.mj.sort;

public class BubbleSort2<E extends Comparable<E>> extends Sort<E>{
    /**
     * 冒泡排序优化1
     * 在循环过程中已经有序，加一个布尔值判断，有序则退出循环
     * 但是多了一些额外指令
     */
    @Override
    protected void sort() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    sorted = false;
                    swap(begin, begin - 1);
                }
            }
            if (sorted) break;
        }
    }
}
