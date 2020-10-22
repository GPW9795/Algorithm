package com.mj;

/**
 * N皇后问题
 * 以行为单位
 */
public class Queens {

    public static void main(String[] args) {
        new Queens().placeQueens(8);
    }

    // 数组索引是行号，数组元素是列号
    // eg.cols[4] = 5 表示第4行的皇后放在第5列
    int[] cols;

    // 一共有多少种摆法
    int ways;

    void placeQueens(int n) {
        if (n < 1) return;
        cols = new int[n];
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
            // 摆放及剪枝
            if (isValid(row, col)) {
                cols[row] = col; // 在第row行第col列摆放皇后
                place(row + 1); // 摆放下一行皇后
                // 回溯
            }
        }
    }

    /**
     * 判断第row行第col列是否可以摆放皇后
     */
    boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            // 斜率为1，所以如果|y1-y2|=|x1-x2|则属于同一斜线
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (col == cols[row]) {
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
