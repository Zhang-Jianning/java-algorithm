package com.zjn.dynamic.programming;

/**
 * 416 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class EquivalentSubset {

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        int[][] memo = new int[nums.length + 1][sum + 1];
        memo[0][0] = 1;
        for (int i = 0; i <= nums.length; i++)
            memo[i][0] = 1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= nums[i - 1]) {
                    // 容量充足
                    memo[i][j] = memo[i - 1][j] + memo[i - 1][j - nums[i - 1]];
                } else {
                    // 容量不足
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }
        // 这里不能判断 > 0，因为最终的结果数据太大了超出了上限，是个负数
        return memo[nums.length][sum] != 0;
    }


    // 使用 boolean 数组
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 取模有余数，是得不到这个和的
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                //背包空间充足
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }


    // 空间压缩
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 取模有余数，是得不到这个和的
        if (sum % 2 == 1) return false;
        sum = sum / 2;
        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {

            // 这里倒这遍历是因为 如果正遍历会覆盖i-1时的dp[j]和dp[i-xxx]，倒遍历时没问题，数据还没有覆盖
            for (int j = sum; j >= 0; j--) {
                //背包空间充足
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }




//    public static void main(String[] args) {
//        int []  s = {100,100};
//        System.out.println(canPartition(s));
//    }



}
