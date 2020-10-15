package com.mj.sort;

public class HeapSort extends Sort {
    private int heapSize;

    @Override
    protected void sort() {
        // 原地建堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);

            // 对0位置进行siftDown（恢复堆的性质）
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        Integer element = array[index];

        int half = heapSize >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            Integer child = array[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize && cmpElement(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            if (cmpElement(element, child) >= 0) break;

            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
