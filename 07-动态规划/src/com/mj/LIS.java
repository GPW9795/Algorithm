package com.mj;

public class LIS {
    public static void main(String[] args) {
        int[] nums = {10, 2, 2, 5, 1, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 二分搜索、牌顶、牌堆
     */
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 牌堆的数量
        int len = 0;
        // 牌顶数组
        int[] top = new int[nums.length];
        for (int num : nums) {
            int begin = 0, end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            top[begin] = num;
            if (begin == len) len++;
        }
        return len;
    }
}
