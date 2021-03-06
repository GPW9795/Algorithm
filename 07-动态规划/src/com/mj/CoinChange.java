package com.mj;

import java.sql.Array;
import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coins(101, new int[]{5, 20, 25}));
    }

    /**
     * 通用实现
     */
    static int coins(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) return -1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i < face) continue;
                if (dp[i - face] < 0 || dp[i - face] >= min) continue;
                min = dp[i - face];
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }

    /**
     * 找零钱的具体方案
     */
    static int coins4(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];
            faces[i] = 1;
            if (i >= 5 && dp[i - 5] < min) {
                min = dp[i - 5];
                faces[i] = 5;
            }
            if (i >= 20 && dp[i - 20] < min) {
                min = dp[i - 20];
                faces[i] = 20;
            }
            if (i >= 25 && dp[i - 25] < min) {
                min = dp[i - 25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
        }
        print(faces, n);
        return dp[n];
    }

    static void print(int[] faces, int i) {
        while (i > 0) {
            System.out.print(faces[i] + " ");
            i -= faces[i];
        }
        System.out.println();
    }

    /**
     * 递推
     */
    static int coins3(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];
            if (i >= 5) min = Math.min(min, dp[i - 5]);
            if (i >= 20) min = Math.min(min, dp[i - 20]);
            if (i >= 25) min = Math.min(min, dp[i - 25]);
            dp[i] = min + 1;
        }
        return dp[n];
    }


    /**
     * 记忆化搜索（自顶向下的调用）
     */
    static int coins2(int n) {
        if (n < 1) return -1;
        int[] dp = new int[n + 1];
        // 初始化数组
        int[] faces = {1, 5, 20, 25};
        for (int face : faces) {
            if (n < face) break;
            dp[face] = 1;
        }
        return coins2(n, dp);
    }

    static int coins2(int n, int[] dp) {
        if (n < 1) return Integer.MAX_VALUE;
        // 没有存过的值
        if (dp[n] == 0) {
            int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
            int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 暴力递归（自顶向下的调用，出现了重叠的子问题）
     */
    static int coins1(int n) {
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 1 || n == 5 || n == 20 || n == 25) return 1;

        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));
        int min = Math.min(min1, min2) + 1;
        return min;
    }
}
