package com.mj.sort;

public class CountingSort extends Sort<Integer> {
    /**
     * 改进版本
     */
    @Override
    protected void sort() {
        // 找出最大、最小值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        // 开辟内存空间
        int[] counts = new int[max - min + 1];
        // 存储每个元素出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }
        // 将有序数组赋值给array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }

    /**
     * 最初版本
     */
    protected void sort0() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 开辟内存空间
        int[] counts = new int[max + 1];
        // 存储每个元素出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        // 更改原数组
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }
        }
    }
}
