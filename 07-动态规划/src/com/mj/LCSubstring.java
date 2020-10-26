package com.mj;

public class LCSubstring {
    public static void main(String[] args) {
        String str1 = "ABCBA";
        String str2 = "BABCA";
        System.out.println(lcs(str1, str2));
    }

    /**
     * 优化 - 一维数组
     */
    static int lcs(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] cs1 = str1.toCharArray();
        if (cs1.length == 0) return 0;
        char[] cs2 = str2.toCharArray();
        if (cs2.length == 0) return 0;

        char[] rowStr = cs1, colStr = cs2;
        if (cs1.length < cs2.length) {
            rowStr = cs2;
            colStr = cs1;
        }
        int[] dp = new int[colStr.length + 1];
        int max = 0;
        for (int i = 1; i <= rowStr.length; i++) {
            for (int j = colStr.length; j > 0; j--) {
                if (rowStr[i - 1] == colStr[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

    /**
     * 二维数组
     */
    static int lcs1(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] cs1 = str1.toCharArray();
        if (cs1.length == 0) return 0;
        char[] cs2 = str2.toCharArray();
        if (cs2.length == 0) return 0;

        int[][] dp = new int[cs1.length + 1][cs2.length + 1];
        int max = 0;
        for (int i = 1; i <= cs1.length; i++) {
            for (int j = 1; j <= cs2.length; j++) {
                if (cs1[i - 1] != cs2[j - 1]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
