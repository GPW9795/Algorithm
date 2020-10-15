package com.mj;

import com.mj.tools.Asserts;
import com.mj.tools.Integers;
import com.mj.tools.Times;

public class Main {

    public static void main(String[] args) {
        Integer[] array = Integers.random(10,1,100);
        SelectionSort(array);
        Asserts.test(Integers.isAscOrder(array));
    }

    /**
     * 选择排序
     */
    static void SelectionSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // 默认0位置最大
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // <=保证稳定性
                if (array[maxIndex] <= array[begin]){
                    maxIndex = begin;
                }
            }
            int tmp = array[maxIndex];
            array[maxIndex] = array[end];
            array[end] = tmp;
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
     * 但是多了一些额外指令
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

    /**
     * 冒泡排序优化2
     * 记录最后一次交换的位置作为下一次的截止位置
     */
    static void BubbleSort2(Integer[] nums) {
        int len = nums.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            // 其初始值在数组完全有序时有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (nums[begin] < nums[begin - 1]) {
                    int tmp = nums[begin];
                    nums[begin] = nums[begin - 1];
                    nums[begin - 1] = tmp;
                    // 记录一下最后一次交换位置
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

    static void testBubble(){
        Integer[] array_1 = Integers.tailAscOrder(1, 10000, 8000);
        Integer[] array_2 = Integers.copy(array_1);
        Integer[] array_3 = Integers.copy(array_1);
        // 冒泡排序
        Times.test("Bubble Sort", () -> {
            BubbleSort(array_1);
        });
        Times.test("Bubble Sort优化1", () -> {
            BubbleSort1(array_2);
        });
        Times.test("Bubble Sort优化2", () -> {
            BubbleSort2(array_3);
        });
    }
}
