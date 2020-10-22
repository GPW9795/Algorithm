package com.mj;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * N皇后问题 - 优化
 */
public class Queens2 {

    public static void main(String[] args) {
        new Queens2().placeQueens(4);
    }

    // 数组索引是行号，数组元素是列号，用于打印
    // eg.cols[4] = 5 表示第4行的皇后放在第5列
    int[] queens;

    // 标记着某一列是否有皇后
    boolean[] cols;

    // 标记着对角线上是否有皇后(左上角 -> 右下角，left top -> right bottom)
    boolean[] leftTop;

    // 标记着对角线上是否有皇后(右上角 -> 左下角，right top -> left bottom)
    boolean[] rightTop;

    // 一共有多少种摆法
    int ways;

    void placeQueens(int n) {
        if (n < 1) return;
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行摆放皇后
     * 递归 + 回溯
     */
    void place(int row) {
        // 最后一行已摆放完，说明有一种摆放方法
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            // 第col列以及斜线已经有皇后了
            if (cols[col]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;

            // 在第row行第col列摆放皇后
            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            // 摆放下一行皇后
            place(row + 1);

            // 回溯，需要重置
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (col == queens[row]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }
}
