package com.mj.sort;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围内的元素进行快速排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        // 确定轴点位置
        int mid = pivotIndex(begin, end);
        // 对子序列进行排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 确定轴点位置
     */
    private int pivotIndex(int begin, int end) {
        // 备份begin位置元素
        E pivot = array[begin];
        // 指向最后一个元素
        end--;

        while (begin < end) {
            // 从右往左
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) { // 右边元素 > 轴点元素
                    end--;
                } else { // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            // 从左往右
            while (begin < end) {
                if (cmp(array[begin], pivot) < 0) { // 左边元素 < 轴点元素
                    begin++;
                } else { // 左边元素 >= 轴点元素
                    array[end--] = array[begin];
                    break;
                }
            }

        }
        // 将轴点元素放入最终位置
        array[begin] = pivot;
        // 返回轴点位置
        return begin;
    }
}
