package com.mj;

public class BinarySearch {
    /**
     * 查找v在有序数组中的位置
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v > array[mid]) {
                begin = mid + 1;
            } else if (v < array[mid]) {
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找v在有序数组中的插入位置
     */
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v >= array[mid]) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }
}
