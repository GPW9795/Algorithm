package com.mj.sort;

import java.awt.image.ImageProducer;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            // 默认0位置最大
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // <=保证稳定性
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
