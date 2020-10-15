package com.mj.sort;

public class BubbleSort1 extends Sort {

    @Override
    protected void sort() {
        int len = array.length;
        // 进行len - 1轮排序
        for (int end = len - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }

            }
        }
    }
}
