package com.mj;

public class LCS {
    public static void main(String[] args) {
        int[] nums1 = {1, 4, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        System.out.println(lcs(nums1, nums2));
    }

    /**
     * 非递归实现
     */
    static int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 递归实现
     */
    static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * 求nums1前i个元素和nums2前j个元素的最长公共子序列长度
     */
    static int lcs(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] != nums2[j - 1]) {
            return Math.max(
                    lcs(nums1, i, nums2, j - 1),
                    lcs(nums1, i - 1, nums2, j)
            );
        }
        return lcs(nums1, i - 1, nums2, j - 1) + 1;
    }
}
