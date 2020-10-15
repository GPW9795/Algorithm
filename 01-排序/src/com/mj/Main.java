package com.mj;

import com.mj.tools.Integers;

public class Main {

    public static void main(String[] args) {
        Integer[] array = Integers.random(10, 1, 100);
        // 冒泡排序
        BubbleSort1(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }

    /**
     * 冒泡排序
     */
    static void BubbleSort(Integer[] nums) {
        int len = nums.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (nums[begin] < nums[begin - 1]) {
                    int tmp = nums[begin];
                    nums[begin] = nums[begin - 1];
                    nums[begin - 1] = tmp;
                }

            }
        }
    }

    /**
     * 冒泡排序优化1
     * 在循环过程中已经有序，加一个布尔值判断，有序则退出循环
     */
    static void BubbleSort1(Integer[] nums) {
        int len = nums.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (nums[begin] < nums[begin - 1]) {
                    sorted = false;
                    int tmp = nums[begin];
                    nums[begin] = nums[begin - 1];
                    nums[begin - 1] = tmp;
                }
            }
            if (sorted) break;
        }
    }

}
