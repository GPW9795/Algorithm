package com.mj.sort;

public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // 开辟内存空间
        int[] counts = new int[10];
        int[] output = new int[array.length];
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider, output, counts);
        }
    }

    protected void countingSort(int divider, int[] output, int[] counts) {
        // 清空数组
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        // 存储每个元素出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素
        for (int i = array.length - 1; i >= 0; i--) {
            output[--counts[array[i] / divider % 10]] = array[i];
        }
        // 将有序数组赋值给array
        for (int i = 0; i < output.length; i++) {
            array[i] = output[i];
        }
    }
}
