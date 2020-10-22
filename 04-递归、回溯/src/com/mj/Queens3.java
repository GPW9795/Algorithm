package com.mj;

/**
 * N皇后问题 - 优化(利用位运算,boolean数组转)
 */
public class Queens3 {

    public static void main(String[] args) {
        new Queens3().place8Queens();
    }

    // 数组索引是行号，数组元素是列号，用于打印
    // eg.cols[4] = 5 表示第4行的皇后放在第5列
    int[] queens;

    // 标记着某一列是否有皇后
    byte cols;

    // 标记着对角线上是否有皇后(左上角 -> 右下角，left top -> right bottom)
    short leftTop;

    // 标记着对角线上是否有皇后(右上角 -> 左下角，right top -> left bottom)
    short rightTop;

    // 一共有多少种摆法
    int ways;

    void place8Queens() {
        queens = new int[8];
        place(0);
        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行摆放皇后
     * 递归 + 回溯
     */
    void place(int row) {
        // 最后一行已摆放完，说明有一种摆放方法
        if (row == 8) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < 8; col++) {
            // 第col列以及斜线已经有皇后了
            int cv = 1 << col;
            if ((cols & cv) != 0) continue;
            int lv = 1 << (row - col + 7);
            if ((leftTop & lv) != 0) continue;
            int rv = 1 << (row + col);
            if ((rightTop & rv) != 0) continue;

            // 在第row行第col列摆放皇后
            queens[row] = col;
            cols |= cv;
            leftTop |= lv;
            rightTop |= rv;
            // 摆放下一行皇后
            place(row + 1);

            // 回溯，需要重置
            cols &= ~cv;
            leftTop &= ~lv;
            rightTop &= ~rv;
        }
    }

    void show() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
