package com.mj.sort;

public class BubbleSort extends Sort{
    @Override
    public void sort() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                }

            }
        }
    }
    /**
     * 冒泡排序优化1
     * 在循环过程中已经有序，加一个布尔值判断，有序则退出循环
     * 但是多了一些额外指令
     */
    public void sort1() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    sorted = false;
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                }
            }
            if (sorted) break;
        }
    }

    /**
     * 冒泡排序优化2
     * 记录最后一次交换的位置作为下一次的截止位置
     */
    public void sort2() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            // 其初始值在数组完全有序时有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                    // 记录一下最后一次交换位置
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

}
